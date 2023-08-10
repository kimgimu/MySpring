package com.kimu.crudboard.domain.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//DAO클래스를 대체한다
@Mapper
public interface PostMapper {

    //게시글 저장
    void save(PostRequest params);

    //게시글 상세보기
    PostResponse findById(Long id);

    //게시글 수정
    void update(PostRequest params);

    //게시글 삭제
    void deleteById(Long id);

    //게시글 리스트 조회 (게시판)
    List<PostResponse> findAll();

    //게시글 카운트 (개수)
    int count();

}
