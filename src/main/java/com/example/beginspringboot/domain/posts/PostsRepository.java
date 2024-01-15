package com.example.beginspringboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {//<Entity, PK 타입>, 이를 상속하면 기본적인 CRUD(!)가 생성됨
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")//함수의 내용을 쿼리형으로 구성하는 것이구나
    List<Posts> findAllDesc();
}
