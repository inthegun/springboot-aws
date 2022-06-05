package com.lsm.tistory.springboot.service.posts;

import com.lsm.tistory.springboot.domain.posts.Posts;
import com.lsm.tistory.springboot.domain.posts.PostsRepository;
import com.lsm.tistory.springboot.web.dto.PostsResponseDto;
import com.lsm.tistory.springboot.web.dto.PostsSaveRequestDto;
import com.lsm.tistory.springboot.web.dto.PostsUpdateRequestDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow( () -> new
                                    IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return  id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow( () -> new
                                    IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    /**
     * update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다.
     * 이게 가능한 이유는 JPA의 영속성 컨텍스트 떄문
     * 영속성 컨텍스트란 : 엔티티를 영구 저장하는 환경
     */
}

/**
 * 스프링에서 Bean을 주입받는 방식
 * @Autowired  , setter  , 생성자
 * 가장 권장하는 방식 생성자 ( @Autowired는 권장하지 않는다 )
 * @RequiredArgsConstructor : final 선언된 필드를 인자값으로 하는 생성자를 롬복을 이용하여 생성
 *
 */