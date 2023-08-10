package com.kimu.crudboard.domain.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private  int viewCnt;
    private Boolean noticeYn;
    private Boolean deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

}
