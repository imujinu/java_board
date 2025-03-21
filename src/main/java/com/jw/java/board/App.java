package com.jw.java.board;

import com.jw.java.board.article.Article;
import com.jw.java.board.article.ArticleController;
import com.jw.java.board.container.Container;
import com.jw.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App {

    public ArticleController articleController;


    App(){
        articleController = Container.articleController;

    }



    void run(){

        System.out.println("== 자바 텍트스 게시판 시작 == ");

        while (true){

            System.out.print("명령)");
            String cmd = Container.sc.nextLine();
            Rq rq = new Rq(cmd);

            if(rq.getUrlPath().equals("/usr/article/write")){
                articleController.doWrite();

            }

            else if(rq.getUrlPath().equals("/usr/article/list")){
                articleController.showList(rq);
            }

            else if (rq.getUrlPath().equals("/usr/article/detail")) {
                articleController.showDetail(rq);
            }
            else if(rq.getUrlPath().equals("/usr/article/modify")){
                articleController.doModify(rq);

            }else if(rq.getUrlPath().equals("/usr/article/delete")){
                articleController.doDelete(rq);
            }
            else if(rq.getUrlPath().equals("exit")){

                break;
            }

            else {
                System.out.println("잘못된 명령어 입니다.");
            }
            System.out.printf("입력받은 명령어 : %s\n", cmd);
        }

        System.out.println("== 자바 텍트스 게시판 끝 == ");
        Container.sc.close();
    }



}
