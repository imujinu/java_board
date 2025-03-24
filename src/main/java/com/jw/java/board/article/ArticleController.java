package com.jw.java.board.article;

import com.jw.java.board.Rq;
import com.jw.java.board.container.Container;
import com.jw.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ArticleController {


    private ArticleService articleService;
    public ArticleController(){
        articleService = Container.articleService;

        articleService.writeArticleData();
    }


    public void doWrite() {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = Container.sc.nextLine();

        if(subject.trim().isEmpty()){
            System.out.println("제목을 입력해주세요");
            return;
        }

        System.out.print("내용 : ");
        String content = Container.sc.nextLine();

        if(content.trim().isEmpty()){
            System.out.println("내용을 입력해주세요");
            return;
        }
        int id = articleService.write(subject,content);

        System.out.printf("%d번 게시물이 등록되었습니다. \n",  id);
    }

    public void showList(Rq rq) {
        String searchKeyword = rq.getParam("searchKeyword", "");
        String orderBy = rq.getParam("orderBy" , "idDesc");

        List<Article> articles = articleService.getArticles(searchKeyword, orderBy);
        if(articles.isEmpty()){
            System.out.println("현재 게시물이 존재하지 않습니다.");
            return;
        }
        Map <String, String> params = rq.getParams();





        System.out.println("==게시물 리스트==");
        System.out.println("번호 | 제목");





        boolean orderByIdDesc = orderBy.equals("idDesc");


        articles.forEach( article ->  System.out.printf("%d | %s\n", article.getId() , article.getSubject()));
    }

    public void showDetail(Rq rq) {
        int id=  rq.getIntParam("id", 0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요");
            return;
        }

        Article article = articleService.findById(id );

        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다. \n",id);
            return;
        }

        System.out.println("==게시물 상세보기==");

            System.out.println("번호 :" + article.getId());
            System.out.println("제목 : " + article.getSubject());
            System.out.println("내용 : " + article.getContent());


    }

    public void doModify(Rq rq) {
        int id=  rq.getIntParam("id", 0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요");
            return;
        }



        Article article = articleService.findById(id);
        System.out.print("새 제목 : ");
        String subject = Container.sc.nextLine();

        if(subject.trim().isEmpty()){
            System.out.println("제목을 입력해주세요");
            return;
        }

        System.out.print("새 내용 : ");
        String content = Container.sc.nextLine();

        if(content.trim().isEmpty()){
            System.out.println("제목을 입력해주세요");
            return;
        }

        articleService.modify(id, subject, content);
        System.out.printf("%d번 게시물이 수정되었습니다.", id);
    }

    public void doDelete(Rq rq) {
        int id=  rq.getIntParam("id", 0);

        if(id==0){
            System.out.println("올바른 값을 입력해주세요");
            return;
        }
        Article article = articleService.findById(id );




        if(article==null){
            System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
            return;
        }

            articleService.delete(id);
            System.out.printf("%d번 게시물을 삭제하였습니다.",id);

    }


}
