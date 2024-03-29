package com.example.beginspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing//JPA Auditing 기능을 사용하려면 이를 활성화 시켜야함. 하지만 WebMvcTest를 사용하기 위해 삭제
@SpringBootApplication//스프링부트의 자동 설정, 스프링 bean 읽기, 생성을 하고, @SpringBootApplication이 있는 위치부터 설정을 읽음->항상 프로젝트의 최상단에 있어야함
public class BeginSpringbootApplication {//프로젝트의 메인 클래스가 될겨

	public static void main(String[] args) {
		SpringApplication.run(BeginSpringbootApplication.class, args);
	}//SpringApplication.run으로 웹서버를 여는겨 -> 톰켓같은 외장 서버가 없어도되는 이유

}
