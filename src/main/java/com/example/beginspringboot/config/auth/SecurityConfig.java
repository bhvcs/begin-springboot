package com.example.beginspringboot.config.auth; //시큐리티 관련 클래스를 담는 곳

import com.example.beginspringboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@RequiredArgsConstructor
@EnableWebSecurity//spring security 설정들을 활성화 해준다
@Configuration
public class SecurityConfig{//OAuth 라이브러리를 이용한 소셜 로그인 설정 코드
    private final CustomOAuth2UserService customOAuth2UserService;
    @Bean//스프링 컨테이너에서 관리하는 객체(인스턴스화 된), 메서드로부터 반환된 객체를 스프링 컨테이너에 등록함
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf((csrfConfig) -> csrfConfig.disable())//csrf: cross site 요청 위조, session 기반 서버가 아닌 rest api server는 여기에 대해 안전함
                .headers((headerConfig)->headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())) //h2-console 화면을 사용하기 위해서 disable하는겨

                .authorizeHttpRequests((authorizeRequests)->authorizeRequests
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name()) //ROLE은 spring security에서 지원해주네
                        .anyRequest().authenticated()
                )
                .logout((logoutConfig)->logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login((loginConfig)->loginConfig.userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService)));
                //oauth2Login: OAuth2 로그인 기능에 대한 여러 설정의 진입점, userInfoEndPoint: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
        //userService: 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록, 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서
        //추가로 진행하고자 하는 기능을 명시할 수 있음
        return http.build();
    }
}
