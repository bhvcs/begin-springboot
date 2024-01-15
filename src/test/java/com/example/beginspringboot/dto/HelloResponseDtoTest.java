package com.example.beginspringboot.dto;

import com.example.beginspringboot.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);//메서드 체이닝
        assertThat(dto.getAmount()).isEqualTo(amount);//롬복이 생성자를 생성했는지 + getter가 잘 되는지 확인한거지
    }
}
