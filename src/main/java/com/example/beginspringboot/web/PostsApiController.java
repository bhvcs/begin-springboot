package com.example.beginspringboot.web;

import com.example.beginspringboot.service.posts.PostsService;
import com.example.beginspringboot.web.dto.PostsResponseDto;
import com.example.beginspringboot.web.dto.PostsSaveRequestDto;
import com.example.beginspringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {//요청으로 들어온 json객체를 자바 객체로 변환해줌
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){//@PathVariable은 url에 포함된 변수를 추출하는겨( {변수명} )
        postsService.delete(id);
        return id;
    }
}
