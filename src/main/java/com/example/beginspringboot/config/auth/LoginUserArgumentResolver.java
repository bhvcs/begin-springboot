package com.example.beginspringboot.config.auth;

import com.example.beginspringboot.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
//indexController를 보면 매번 session에서 정보를 받게 됨. 이를 개선하기 위해 @LoginUser까지 만들었다.
@RequiredArgsConstructor
@Component //bean 객체로 만든다, 이런 기능을 가진 애가 spring이 관리해주도록 하는건가?
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {//이는 조건에 맞는 메서드가 있을 경우
    // HandlerMethodArgumentResolver의 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception{
        return httpSession.getAttribute("user");
    }
}
