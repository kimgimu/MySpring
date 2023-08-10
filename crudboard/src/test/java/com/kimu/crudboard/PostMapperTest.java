package com.kimu.crudboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    @Test
    void findById(){
        PostResponse postResponse = postMapper.findById(3L);

        try {
            //java객체를 json으르 변환하는 코드
            String postJson1 = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(postResponse);
            System.out.println(postJson1);
            String postJson2 = new ObjectMapper().writeValueAsString(postResponse);
            System.out.println(postJson2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        // 1. 게시글 수정
        PostRequest params = new PostRequest();
        params.setId(1L);
        params.setTitle("2번 게시글 제목 수정합니다.");
        params.setContent("2번 게시글 내용 수정합니다.");
        params.setWriter("도뎡이");
        params.setNoticeYn(true);
        postMapper.update(params);

        // 2. 게시글 상세정보 조회
        PostResponse post = postMapper.findById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void delete(){
        System.out.println("삭제 이전 전체 게시글 수" + postMapper.findAll().size()+ "개입니다");
        postMapper.deleteById(3L);
        System.out.println("삭제 이후 전체 게시글 수" + postMapper.findAll().size()+ "개입니다");
    }




}
