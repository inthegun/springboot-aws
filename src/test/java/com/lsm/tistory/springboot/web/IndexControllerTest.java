package com.lsm.tistory.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 별다른 설정 없이 SpringBootTest 를 사용할 경우 H2 데이터베이스를 자동으로 실행
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 이 테스트는 실제로 URL 호출 시 페이지의 내용이 제대로 호출되는지에 대한 테스트
     */
    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/",String.class);

        // then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }
}