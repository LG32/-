package com.example.administrator.newfridge.okhttp;

import java.util.HashMap;
import java.util.Map;

public class UrlMap  {
    private Map<String, String> urlMap;
    private String urlHead = "http://47.95.247.236:8080/";

    public UrlMap(){
        urlMap = new HashMap<> (  );
        initMap ();
    }

    private void initMap(){
        urlMap.put ( "login", urlHead + "login/" );
        urlMap.put ( "getiden", urlHead + "/validations/getiden" );
        urlMap.put ( "register", urlHead + "/validations/register" );
    }

    public String getUrl(String key){
        return urlMap.get ( key );
    }
}
