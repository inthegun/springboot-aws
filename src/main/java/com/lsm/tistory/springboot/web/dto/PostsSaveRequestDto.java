package com.lsm.tistory.springboot.web.dto;

import com.lsm.tistory.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    /**
     * Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했다.
     * 하지만 절대로 Entity 클래스를 Request / Response 클래스로 사용해서는 안된다.
     * Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스
     *
     */
}
