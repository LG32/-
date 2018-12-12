package com.example.administrator.newfridge.okhttp;

import java.util.HashMap;
import java.util.Map;

public class UrlMap {

    private Map<String, String> urlMap;

    private static UrlMap url;

    //    private String urlHead = "http://47.95.247.236:8080";
    private String urlHead = "http://192.168.199.100:8080";

    private UrlMap() {
        urlMap = new HashMap<> ();
        initMap ();
    }

    public static UrlMap getUrlMap(){
        if (url == null) {
            url = new UrlMap ();
            return url;
        }
        else
            return url;
    }

    private void initMap() {
        urlMap.put ( "login", urlHead + "/login/" );
        urlMap.put ( "getiden", urlHead + "/validations/getiden" );
        urlMap.put ( "register", urlHead + "/validations/register" );
        urlMap.put ( "getBoxId", urlHead + "/iceboxes/getmyiceboxid" );
        urlMap.put ( "getFoodList", urlHead + "/foods/getallfoodlist/" );
    }

    public String getUrl(String key) {
        return urlMap.get ( key );
    }
}
