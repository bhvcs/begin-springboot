package com.example.beginspringboot.web;

import com.example.beginspringboot.config.auth.LoginUser;
import com.example.beginspringboot.config.auth.dto.SessionUser;
import com.example.beginspringboot.service.posts.PostsService;
import com.example.beginspringboot.web.dto.PostsResponseDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {//완전 화면만 필요한 곳 <-> PostApiController: api를 통해서 변화가 이뤄져야 할 때 쓰는 곳

    private final PostsService postsService;//생성자를 통한 의존성 주입을 받기 위해 final과 @RequiredArgsConstructor
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        //SessionUser user = (SessionUser) httpSession.getAttribute("user");//CustomOAuth2UserService에서 로그인 성공 시에 세션에 sessionUser을 저장 할 것임
//세션에서 정보를 받아오는 것이 여러 부분에서 중복될 수 있음 -> 이를 메서드 인자로 세션값을 바로 받을 수 있도록 개선하자
        if( user != null ){
            model.addAttribute("userName", user.getName());
        }
        return "index";//머스테치 스타터 덕에 컨트롤러에서 문자열을 반환하는 경우, src/main/resources/templates/ + 문자열 + .mustache로 자동 변환
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")//수정 가능한 페이지로 랜더링
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
