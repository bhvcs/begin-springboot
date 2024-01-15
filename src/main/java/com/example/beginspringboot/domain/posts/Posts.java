package com.example.beginspringboot.domain.posts;



import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter//Entity class에서는 절대 Setter를 만들지 않음 -> 생성자와 해당 이벤트에 맞는 public 메서드를 통해
@NoArgsConstructor
@Entity//JPA annotation, 테이블에 링크될 클래스임을 나타낸다
public class Posts extends BaseTimeEntity {//BaseTimeEntity를 상속받아서 자동으로 생성/변경 날짜 등록되게끔 할 것이다.
    @Id//해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//GeneratedValue는 PK생성 규칙, GenerationType.Identity는 auto_increment 옵션
    private Long id;

    @Column(length = 500, nullable = false)//해당 클래스의 필드, column
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){//update비지니스 로직은 도메인에 있어야함
        this.title = title;
        this.content = content;
    }
}
