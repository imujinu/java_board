package com.jw.java.board.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
 @Data
public class Article{

    private int id;
    private String subject;
    private String content;

}


