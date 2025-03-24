package com.jw.java.board.article;

import com.jw.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private List<Article> articles;
    private int lastId;

    public ArticleRepository(){
        articles = new ArrayList<>();
        lastId = 0;
    }

    public int write(String subject, String content) {
        int id = ++lastId;

        Article article = new Article(id, subject, content);

        articles.add(article);
        return id;
    }

    public List <Article> getSortedArticles(String orderBy){
        List<Article> sortedArticles = articles;

        if(orderBy.equals("idAsc")){
            return articles;
        }
        if (orderBy.equals("idDesc")){
            sortedArticles = Util.reverseList(articles);

        }

        return sortedArticles;

    }

    public List<Article> getArticles(String searchKeyword, String orderBy) {
        List<Article> filteredArticles = getSortedArticles(orderBy);

        if(!searchKeyword.trim().isEmpty()){

            filteredArticles = new ArrayList<>();

            for(Article article : articles){
                boolean matched = article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword);
                if(matched)filteredArticles.add(article);
            }
        }
        return filteredArticles;
    }

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst() // 첫 번째 요소 찾기
                .orElse(null);
    }

    public void modify(int id, String subject, String content) {
        Article article= findById(id);

        if(article != null){
            article.setSubject(subject);
            article.setContent(content);
        }
    }

    public void delete(int id) {
        Article article= findById(id);
        if(article!= null){
            articles.remove(article);
        }
    }
}
