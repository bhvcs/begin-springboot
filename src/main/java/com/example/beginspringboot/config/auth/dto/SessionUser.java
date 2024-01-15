package com.example.beginspringboot.config.auth.dto;

import com.example.beginspringboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {//인증된 사용자 정보만 있으면 됨
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
    //session클래스를 만든 이유: 세션에 User클래스를 직접 넣을 경우 직렬화를 하라는 오류가 나옴,
    // User는 Entity고 다른 entity와 어떤 관계가 있을지 모르는데 그럴 때마다 직렬화를 다 신경써줘야함,
    // 차라리 직렬화 기능을 가진 세션 Dto를 만드는 것이 낫다는 판단

}
