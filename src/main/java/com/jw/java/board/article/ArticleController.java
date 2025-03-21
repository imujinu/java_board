package com.jw.java.board.article;

import com.jw.java.board.Rq;
import com.jw.java.board.container.Container;
import com.jw.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {
    private List<Article> articles;
    private int lastArticleId;

    public ArticleController(){
        articles = new ArrayList<>();
        lastArticleId = 0;
        writeArticleData();
    }
    void writeArticleData(){
        IntStream.rangeClosed(1,100).forEach(i->articles.add(new Article(i,"제목" + i , "내용" + i)));}

    public void doWrite() {
        lastArticleId = articles.get(articles.size()-1).id;
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();
        System.out.print("내용 : ");
        String content = Container.sc.nextLine();
        int id = ++lastArticleId;

        Article article = new Article(id, subject, content);
        articles.add(article);
        System.out.println("생성 된 게시물 : " + article);
        System.out.printf("%d번 게시물이 등록되었습니다. \n",  article.id);
    }

    public void showList(Rq rq) {
        if(articles.isEmpty()){
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }
        Map <String, String> params = rq.getParams();


        List<Article> filteredArticles = articles;



        System.out.println("==게시물 리스트==");
        System.out.println("번호 | 제목");


        if(params.containsKey("searchKeyword")){
            String searchKeyword = params.get("searchKeyword");

            filteredArticles = new ArrayList<>();

            for(Article article : articles){
                boolean matched = article.subject.contains(searchKeyword) || article.content.contains(searchKeyword);
                if(matched)filteredArticles.add(article);
            }
        }

        boolean orderByIdDesc = true;
        if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")){
            orderByIdDesc = false;

        }
        List<Article> sortedArticles = filteredArticles;
        if (orderByIdDesc){
            sortedArticles = Util.reverseList(sortedArticles);

        }
        sortedArticles.forEach( article ->  System.out.printf("%d | %s\n", article.id , article.subject));
    }

    public void showDetail(Rq rq) {
        System.out.println("==게시물 상세보기==");

        Map<String, String> params = rq.getParams();
        int id= 0;
        if(articles.isEmpty()){
            System.out.println("작성된 게시글이 존재하지 않습니다.");
        }

        if(!params.containsKey("id")){
            System.out.println("id 값을 입력해주세요");
            return;
        }

        try{ id = Integer.parseInt(params.get("id"));} catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요");
            return;


        }

        if(id > articles.size()){
            System.out.println(id+"번 게시물은 존재하지 않습니다.");
            return;
        }
        Article article = findById(articles ,id );

        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다. \n",id);
        }
        else{
            System.out.println("번호 :" + article.id);
            System.out.println("제목 : " + article.subject);
            System.out.println("내용 : " + article.content);

        }
    }

    public void doModify(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id= 0;
        if(articles.isEmpty()){
            System.out.println("작성된 게시글이 존재하지 않습니다.");
        }

        if(!params.containsKey("id")){
            System.out.println("id 값을 입력해주세요");
            return;
        }

        try{ id = Integer.parseInt(params.get("id"));} catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요");
            return;


        }

        if(id > articles.size()){
            System.out.println(id+"번 게시물은 존재하지 않습니다.");
            return;
        }

        Article article = articles.get(id-1);
        System.out.print("새 제목 : ");
        article.subject = Container.sc.nextLine();
        System.out.print("새 내용 : ");
        article.content = Container.sc.nextLine();
        System.out.printf("%d번 게시물이 수정되었습니다.", id);
    }

    public void doDelete(Rq rq) {
        Map<String, String> params = rq.getParams();
        int id= 0;
        if(articles.isEmpty()){
            System.out.println("작성된 게시글이 존재하지 않습니다.");
        }

        if(!params.containsKey("id")){
            System.out.println("id 값을 입력해주세요");
            return;
        }

        try{ id = Integer.parseInt(params.get("id"));} catch (NumberFormatException e) {
            System.out.println("id를 정수형태로 입력해주세요");
            return;


        }

        if(id > articles.size()){
            System.out.println(id+"번 게시물은 존재하지 않습니다.");
            return;
        }

        Article article = findById(articles,id);

        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
            return;
        }
        else{
            articles.remove(article);
            System.out.printf("%d번 게시물을 삭제하였습니다.",id);
            return;
        }
    }

    private Article findById(List<Article> articles, int id) {
//        for(Article article : articles){
//            if(article.id == id){
//               return article;
//
//            }
//
//        }

        return  articles.stream()
                .filter(article -> article.id == id)
                .findFirst() // 첫 번째 요소 찾기
                .orElse(null);
    }
}
