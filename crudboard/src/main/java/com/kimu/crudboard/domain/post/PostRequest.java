package com.kimu.crudboard.domain.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;

}
