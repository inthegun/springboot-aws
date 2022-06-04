package com.lsm.tistory.springboot.service.posts;

import com.lsm.tistory.springboot.domain.posts.PostsRepository;
import com.lsm.tistory.springboot.web.dto.PostsSaveRequestDto;
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
}

/**
 * 스프링에서 Bean을 주입받는 방식
 * @Autowired  , setter  , 생성자
 * 가장 권장하는 방식 생성자 ( @Autowired는 권장하지 않는다 )
 * @RequiredArgsConstructor : final 선언된 필드를 인자값으로 하는 생성자를 롬복을 이용하여 생성
 *
 */