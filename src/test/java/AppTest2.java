import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppTest2 {
    public static void main(String[] args) {

        Rq rq = new Rq("/user/article/write?id=1&subject=제목1&content=10+5=31&writerName=김철수&boardId=1");
        Map<String, String> params = rq.getParams();
        System.out.println(params);

        String urlPath = rq.getUrlPath();
        System.out.println(urlPath);
    }


}
class Rq{
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url){
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getPathFromUrl(this.url);
    }
    Map<String, String> getParams(){


        return params;
    }
    String getUrlPath(){


        return urlPath;
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

    public static String getPathFromUrl(String url) {
    return url.split("\\?",2)[0];
    }
}
