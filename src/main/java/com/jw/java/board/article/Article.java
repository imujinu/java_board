package com.jw.java.board.article;

public class Article{
    public int id;
    public String subject;
    public String content;
    Article(int id, String subject, String content){
        this.id = id;
        this.subject = subject;
        this.content = content;
    }
    @Override
    public String toString(){
        return "{id : %d, subject : \"%s\", content : \"%s\"}".formatted(id,subject,content);
    }
}


