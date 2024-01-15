package com.example.beginspringboot.web.dto;

import com.example.beginspringboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){//PostsResponseDto는 어차피 entity에서 받아온 정보만 사용할거니깐 entity를 받아 필드에 값을 넣음
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
