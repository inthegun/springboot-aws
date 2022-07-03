package com.lsm.tistory.springboot.web;

import com.lsm.tistory.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model) {
        // Model : 서버 템플릿 엔진에서 사용할수 있는 객체를 저장할 수 있다.
        // 여기서는 postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }
    /**
     * 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환 할때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
     * 앞의 경로 : src/main/resources/templates
     * 뒤의 확장자 : .mustache
     */
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


}
