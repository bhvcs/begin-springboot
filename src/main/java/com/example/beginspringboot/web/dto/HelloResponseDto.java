package com.example.beginspringboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//선언된 모든 필드에 대한 getter 생성
@RequiredArgsConstructor//final로 선언된 필드를 포함한 생성자를 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
