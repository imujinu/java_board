import java.util.*;

public class AppTest {
    public static void main(String[] args) {
        // 파라미터 분석

        String queryString = "id=1&subject=제목1&content=내용1&writerName=김철수&boardId=1";
        String[] queryStringBits = queryString.split("&");



        Map<String, String> params = new HashMap<>();
        for (String bit : queryStringBits){
            String[] bitBits = bit.split("=");
            params.put(bitBits[0], bitBits[1]);




        }

        System.out.println("==원하는 값만 가져와서 출력");
        System.out.printf("id : %d\n", Integer.parseInt(params.get("id")) );
        System.out.printf("subject : %s\n", params.get("subject") );
        System.out.printf("content : %s\n", params.get("content") );
        System.out.printf("writerName : %s\n", params.get("writerName") );
        System.out.printf("boardId : %d\n", Integer.parseInt(params.get("boardId")) );


        // {subject=제목1, boardId=1, id=1, content=내용1, writerName=김철수} Map은 순서가 없다
        // {id=1, subject=제목1, content=내용1, writerName=김철수, boardId=1} HashMap은 순서를 보장한다.

        System.out.println("==반복문을 이용한 순회==");
        params.forEach((key,value)->{
            System.out.printf("%s : %s\n" , key, value);
        });
    }
}
