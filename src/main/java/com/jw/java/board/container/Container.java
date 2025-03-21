package com.jw.java.board.container;

import com.jw.java.board.article.ArticleController;

import java.util.Scanner;

public class Container {
   public static Scanner sc;

   public static ArticleController articleController;

   static {
      sc = new Scanner(System.in);
      articleController = new ArticleController();
   }
}
