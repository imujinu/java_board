import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppTest2 {
    public static void main(String[] args) {
        String queryString = "/user/article/write?id=1&subject=제목1&content=10+5=31&writerName=김철수&boardId=1";
        Map<String, String> params = Util.getParamsFromUrl(queryString);
        System.out.println(params);
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
}
