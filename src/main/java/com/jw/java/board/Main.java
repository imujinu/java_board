package com.jw.java.board;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("== 자바 텍트스 게시판 시작 == ");
        Scanner sc = new Scanner(System.in);

        List<Article> articles = new ArrayList<>();
            int lastArticleId = 1;
            Article lastArticle = null;
        while (true){


        System.out.print("명령)");
        String cmd = sc.nextLine();

        if(cmd.equals("write")){
            System.out.println("== 게시물 작성 ==");
            System.out.print("제목 : ");
            String subject = sc.nextLine();
            System.out.print("내용 : ");
            String content = sc.nextLine();
            int id = lastArticleId++;

            Article article = new Article(id, subject, content);
            lastArticle = article;
            articles.add(article);
            System.out.println("생성 된 게시물 : " + article);
            System.out.printf("%d번 게시물이 등록되었습니다. \n",  article.id);
            }else if(cmd.equals("list")){
            if(articles.isEmpty()){
                System.out.println("현재 게시물이 존재하지 않습니다.");
                continue;
            }
            System.out.println("번호 | 제목");
//            v2. for문
//            for(Article article : articles){
//
//                System.out.printf("%d | %s\n", article.id , article.subject);
//            }

//            v3. forEach문
//            articles.forEach(article ->  System.out.printf("%d | %s\n", article.id , article.subject));

//            v4. 역순 정렬
            for(int i = articles.size()-1; i>= 0 ; i--){
                Article article = articles.get(i);
                System.out.printf("%d | %s\n", article.id , article.subject);
            }
        }

        else if (cmd.equals("detail")) {
            System.out.println("==게시물 상세보기==");
            Article article = lastArticle;

            if(article==null){
                System.out.println("작성된 게시글이 존재하지 않습니다.");
                continue;
            }
            System.out.println("번호 :" + article.id);
            System.out.println("제목 : " + article.subject);
            System.out.println("내용 : " + article.content);

        } else if(cmd.equals("exit")){

        break;
}else {
            System.out.println("잘못된 명령어 입니다.");
        }
        System.out.printf("입력받은 명령어 : %s\n", cmd);
        }

        System.out.println("== 자바 텍트스 게시판 끝 == ");
        sc.close();
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
