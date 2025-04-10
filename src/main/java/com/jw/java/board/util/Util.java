package com.jw.java.board.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util{
    public static Map<String, String> getParamsFromUrl(String queryString){
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

    public static <T> List<T> reverseList(List<T> list){
        List<T> reverse = new ArrayList<>(list.size());

        for (int i = list.size()-1; i>=0 ; i--){
            reverse.add(list.get(i));
        }

        return reverse;
    }
}
