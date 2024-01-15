package com.example.beginspringboot.web;

import com.example.beginspringboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//컨트롤러를 json을 반환하는 컨트롤러로 만들어줌, @Controller(컨트롤러 지정해주는 annotation) + @ResponseBody
public class HelloController {
    
    @GetMapping("/hello")//http Get 요청을 받을 수 있는 API를 만들어줌
    public String hello(){
        return "hello";
    }//HelloControllerTest에서 test해보기

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){//@RequestParam은 외부 API에서 받아온 파라미터를 가져오는 어노테이션
        return new HelloResponseDto(name, amount);
    }
}
