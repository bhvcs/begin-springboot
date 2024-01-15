package com.example.beginspringboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);//Optional은 <T>를 감싸주는 Wrapper class, NullPointerException을 방지해줌
    //jpa는 메서드의 이름으로 쿼리를 생성해줌 -> findByEmail
}
