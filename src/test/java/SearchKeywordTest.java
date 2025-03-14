import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;


public class SearchKeywordTest {
    public static void main(String[] args) {
       List <Article> articles = new ArrayList<>();

        IntStream.rangeClosed(1,5).forEach(i-> articles.add(new Article(i , "제목" + i, "내용" + i)));

        articles.add(new Article(6, "자바는 재밌습니까?", "자바를 처음 공부하는데"));

        String searchKeyword = "자바";

        List<Article> filteredArticles = articles.stream().filter(article -> article.subject.contains(searchKeyword)).toList();
        System.out.println(filteredArticles);
//        for(Article article : articles){
//            if( article.subject.contains(searchKeyword)){
//                System.out.println(article);
//            }
//        }
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