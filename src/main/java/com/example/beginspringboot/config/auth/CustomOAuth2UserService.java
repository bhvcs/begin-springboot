package com.example.beginspringboot.config.auth;

import com.example.beginspringboot.config.auth.dto.OAuthAttributes;
import com.example.beginspringboot.config.auth.dto.SessionUser;
import com.example.beginspringboot.domain.user.User;
import com.example.beginspringboot.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {//구글 로그인 이휴 가져온 사용자의 정보들을 기반, 가입 및 정보수정, 세션 저장 등의 기능을 지원한다.
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {//(my: 가입 기능 같음)OAuth -> User로 변환하는 요청을 받으면 그 요청을 통해서 OAuth2User 객체로 만들어줘야되는겨
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();//OAuth로 얻은 user 정보를 User로 변환할 때 기본값을 일단 가져온다
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();//로그인 하는 서비스(구글, 카카오 같은)를 구분하는 id
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        //OAuth2 로그인 진행 시에 키가 되는 필드 값, 잘 모르겠
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuthAttributes: OAuth2UserService를 통해 가져온 OAuth2User의 attributes를 담을 클래스, 다른 소셜 로그인도 이 클래스를 사용하니 만들어야겠지
        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));//SessionUser: 세션에 사용자 정보를 저장하기 위한 Dto 클래스

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes){//구글 사용자 정보가 업데이트 되었을 때 User 엔티티에도 반영됨
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }


}
