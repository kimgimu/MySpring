package com.kimu.crudboard;

import com.kimu.crudboard.domain.post.PostMapper;
import com.kimu.crudboard.domain.post.PostRequest;
import com.kimu.crudboard.domain.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Test
    void save(){
        PostRequest param = new PostRequest();
        param.setTitle("1번 게시글 제목");
        param.setContent("기무의 내용");
        param.setWriter("김기무");
        param.setNoticeYn(false);
        postMapper.save(param);


        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 수는 " + posts.size() + "개입니다");
    }


}
