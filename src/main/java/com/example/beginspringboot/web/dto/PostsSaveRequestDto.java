package com.example.beginspringboot.web.dto;

import com.example.beginspringboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {//Entity는 db와 맞닿아있다. 이를 직접 이용하는 것은 좋지 않음. 그래서 dto로
    private String title;//request/response용 Dto는 view를 위한 클래스라 자주 바뀜, 중요한 Entity 클래스가 자주 바뀌면 안좋자나
    private String content;//=>View Layer와 DB Layer 분리
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
