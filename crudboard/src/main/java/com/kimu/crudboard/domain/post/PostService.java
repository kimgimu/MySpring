package com.kimu.crudboard.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//레거시일때는  @Autowired로 빈을 주입하였으나,
//생성자로 빈을 주입하는 방식 (권장방식)
@RequiredArgsConstructor
public class PostService {

    //@RequiredArgsConstructor 어노테이션을 사용하면
    //클래스 내에 final로 선언된 모든 멤버에 대한 생성자를 만들어주는 역할을 해준다
    private final PostMapper postMapper;

    //게시글 저장
    //선언적 트랙잭션 기능을 해주는 어노테이션
    //메서드의 실행과 동시에 트랜잭션 시작, 메서드의 정상종료 여부에 따라 Commit or Rollback 된다
    @Transactional
    public Long savePost(final PostRequest params){
        postMapper.save(params);
        
        //성공 한다면 값이 바뀐다 
        //(PostMapper.xml에의 해당 insert구문에 useGeneratedKeys="true" keyProperty="id" 넣어줘야한다)
        return params.getId();
    }

    //게시글 상세정보 조회
    public PostResponse findPostById(final Long id){
        return postMapper.findById(id);
    }

    //게시글 수정
    @Transactional
    public Long updatePost(final PostRequest params){
        postMapper.update(params);
        return params.getId();
    }

    //게시글 삭제
    public Long deletePost(final Long id){
        postMapper.deleteById(id);
        return id;
    }

    public List<PostResponse> findAllPost(){
        return postMapper.findAll();
    }



}
