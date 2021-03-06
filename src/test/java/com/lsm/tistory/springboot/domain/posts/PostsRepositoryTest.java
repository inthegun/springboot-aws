package com.lsm.tistory.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest // 별다른 설정 없이 SpringBootTest 를 사용할 경우 H2 데이터베이스를 자동으로 실행
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /**
     * Junit 에서 단위테스트가 끝날때마다 수행되는 메소드 지정
     * 보통은 배포 전 전체 테스트를 수행할때 테스트간 데이터 침범을 막기 위해 사용
     * 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2 에 데이터가 그대로 남아 있어 다음 테스트 실행시
     * 테스트가 실패할수 있음
     */
    @AfterEach // JUnit 4 : @After -> JUnit 5 : @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /**
         * 테이블 posts 에 insert / update 쿼리를 실행
         * id 값이 있다면 update , 없다면 insert 쿼리
         */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("lsm@naver.com")
                .build()
        );

        // when
        /**
         * 테이블 posts 에 있는 모든 데이터를 조회해오는 메소드     */
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createDate="+posts.getCreatedDate()+", modefiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}