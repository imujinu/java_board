package com.jw.java.board;


import com.jw.java.board.container.Container;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static List<Article> articles = new ArrayList<>();
    static int lastArticleId = 0;


    static void writeArticleData(){
        IntStream.rangeClosed(1,100).forEach(i->articles.add(new Article(i,"제목" + i , "내용" + i)));

        }
    public static void main(String[] args) {
        System.out.println("== 자바 텍트스 게시판 시작 == ");

        writeArticleData();

        while (true){

        System.out.print("명령)");
        String cmd = Container.sc.nextLine();
        Rq rq = new Rq(cmd);

        if(rq.getUrlPath().equals("/usr/article/write")){
            actionUserArticleWrite();

            }

        else if(rq.getUrlPath().equals("/usr/article/list")){
            actionUserArticleList(rq);
        }

        else if (rq.getUrlPath().equals("/usr/article/detail")) {
           actionUserArticleDetail(rq);
        } else if(rq.getUrlPath().equals("exit")){

        break;
}
        else if(rq.getUrlPath().equals("/usr/article/modify")){
            actionUserArticleModify(rq);

        }else if(rq.getUrlPath().equals("/usr/article/delete")){
            actionUserArticleDelete(rq);

        }

        else {
            System.out.println("잘못된 명령어 입니다.");
        }
        System.out.printf("입력받은 명령어 : %s\n", cmd);
        }

        System.out.println("== 자바 텍트스 게시판 끝 == ");
        Container.sc.close();
    }

    private static void actionUserArticleDelete(Rq rq) {
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

    private static Article findById(List<Article> articles, int id) {
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

    private static void actionUserArticleModify(Rq rq) {
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

    private static void actionUserArticleWrite() {
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

    private static void actionUserArticleDetail(Rq rq) {
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

    private static void actionUserArticleList(Rq rq) {

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

}
   class Article{
    int id;
    String subject;
    String content;
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

class Rq{
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url){
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getPathFromUrl(this.url);
    }
    Map<String, String> getParams(){


        return params;
    }
    String getUrlPath(){


        return urlPath;
    }
}


class Util{
    static Map<String, String> getParamsFromUrl(String queryString){
        Map<String, String> params = new HashMap<>();
        String[] urlBits = queryString.split("\\?",2);

        if(urlBits.length ==1 )return params;

        for(String bit : urlBits[1].split("&")){
            String[] bits = bit.split("=",2);
            params.put(bits[0], bits[1]);
        }
        return params;

    }

    public static String getPathFromUrl(String url) {
        return url.split("\\?",2)[0];
    }

    public static <T> List<T> reverseList(List<T> list){
        List<T> reverse = new ArrayList<>(list.size());

        for (int i = list.size()-1; i>=0 ; i--){
            reverse.add(list.get(i));
        }

        return reverse;
    }
}
