package com.jw.java.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("== 자바 텍트스 게시판 시작 == ");
        Scanner sc = new Scanner(System.in);

            int id = 1;
        while (true){


        System.out.print("명령)");
        String cmd = sc.nextLine();

        if(cmd.equals("write")){
            System.out.println("== 게시물 작성 ==");
            System.out.print("제목 : ");
            String subject = sc.nextLine();
            System.out.print("내용 : ");
            String content = sc.nextLine();
            int number = id++;

            Article article = new Article();
            article.id = number;
            article.subject = subject;
            article.content = content;
            System.out.println("생성 된 게시물 : " + article);
            System.out.printf("%d번 게시물이 등록되었습니다. \n",  article.id);
        }

        else if(cmd.equals("exit")){

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
    @Override
    public String toString(){
        return "{id : %d, subject : \"%s\", content : \"%s\"}".formatted(id,subject,content);
    }
    }
