package com.jw.java.board;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("== 자바 텍트스 게시판 시작 == ");
        Scanner sc = new Scanner(System.in);

        while (true){
        System.out.println("명령)");
        String cmd = sc.nextLine();

        if(cmd.equals("write")){
            System.out.println("== 게시물 작성 ==");
            System.out.print("제목 : ");
            String subject = sc.nextLine();
            System.out.print("내용 : ");
            String content = sc.nextLine();
            int id = 1;
            System.out.printf("%d번 게시물이 등록되었습니다. \n", id);
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