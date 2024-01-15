package com.example.beginspringboot;

import com.example.beginspringboot.config.auth.SecurityConfig;
import com.example.beginspringboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})//스프링 테스트 어노테이션 중 web에 집중할 수 있는 annotation, 컨트롤러만 사용할 때 선언
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;//웹 API를 테스트할 때 사용(http get, post 등)

    @WithMockUser(roles="USER")
    @Test
    public void hello리턴_확인() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())//mvc.perform의 결과로 나온 http header의 status를 검증
                .andExpect(content().string(hello));//mvc.perform의 결과로 응답의 본문을 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))//jsonPath: Json 응답값!을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
