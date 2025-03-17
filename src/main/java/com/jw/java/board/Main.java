package com.jw.java.board;


import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static void writeArticleData(List<Article> articles){
        IntStream.rangeClosed(1,100).forEach(i->articles.add(new Article(i,"제목" + i , "내용" + i)));

        }
    public static void main(String[] args) {
        System.out.println("== 자바 텍트스 게시판 시작 == ");
        Scanner sc = new Scanner(System.in);

        List<Article> articles = new ArrayList<>();
        writeArticleData(articles);

            int lastArticleId = articles.get(articles.size()-1).id;
        while (true){

        System.out.print("명령)");
        String cmd = sc.nextLine();
        Rq rq = new Rq(cmd);

        if(rq.getUrlPath().equals("/usr/article/write")){
            System.out.println("== 게시물 작성 ==");
            System.out.print("제목 : ");
            String subject = sc.nextLine();
            System.out.print("내용 : ");
            String content = sc.nextLine();
            int id = ++lastArticleId;

            Article article = new Article(id, subject, content);
            articles.add(article);
            System.out.println("생성 된 게시물 : " + article);
            System.out.printf("%d번 게시물이 등록되었습니다. \n",  article.id);
            }

        else if(rq.getUrlPath().equals("/usr/article/list")){
            actionUserArticleList(rq,articles);
        }

        else if (rq.getUrlPath().equals("/usr/article/detail")) {
           actionUserArticleDetail(rq, articles);
        } else if(rq.getUrlPath().equals("exit")){

        break;
}else {
            System.out.println("잘못된 명령어 입니다.");
        }
        System.out.printf("입력받은 명령어 : %s\n", cmd);
        }

        System.out.println("== 자바 텍트스 게시판 끝 == ");
        sc.close();
    }

    private static void actionUserArticleDetail(Rq rq, List<Article> articles) {
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


        Article article = articles.get(id-1);
        System.out.println("번호 :" + article.id);
        System.out.println("제목 : " + article.subject);
        System.out.println("내용 : " + article.content);

    }

    private static void actionUserArticleList(Rq rq, List<Article> articles) {

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
