package com.jw.java.board;

import com.jw.java.board.util.Util;

import java.util.Map;

public class Rq{
    String url;
    Map<String, String> params;
    String urlPath;

    Rq(String url){
        this.url = url;
        params = Util.getParamsFromUrl(this.url);
        urlPath = Util.getPathFromUrl(this.url);
    }
    public Map<String, String> getParams(){


        return params;
    }
    String getUrlPath(){


        return urlPath;
    }

    public int getIntParam(String paramName, int defaultValue){
        if(!params.containsKey(paramName)){
            return defaultValue;
        }
        try{
            return Integer.parseInt(params.get(paramName));

        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public String getParam(String paramName, String defaultValue){
        if(!params.containsKey(paramName)){
            return defaultValue;
        }
        return params.get(paramName);
    }

}
