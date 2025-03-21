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
}
