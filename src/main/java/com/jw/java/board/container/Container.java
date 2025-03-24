package com.jw.java.board.container;

import com.jw.java.board.article.ArticleController;
import com.jw.java.board.article.ArticleRepository;
import com.jw.java.board.article.ArticleService;

import java.util.Scanner;

public class Container {
   public static Scanner sc;

   public static ArticleController articleController;
   public static ArticleService articleService;
   public static ArticleRepository articleRepository;
   static {
      sc = new Scanner(System.in);
      articleRepository = new ArticleRepository();
      articleService = new ArticleService();
      articleController = new ArticleController();
   }
}
