package com.example.beginspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//스프링부트의 자동 설정, 스프링 bean 읽기, 생성을 하고, @SpringBootApplication이 있는 위치부터 설정을 읽음->항상 프로젝트의 최상단에 있어야함
public class BeginSpringbootApplication {//프로젝트의 메인 클래스가 될겨

	public static void main(String[] args) {
		SpringApplication.run(BeginSpringbootApplication.class, args);
	}//SpringApplication.run으로 웹서버를 여는겨 -> 톰켓같은 외장 서버가 없어도되는 이유

}
