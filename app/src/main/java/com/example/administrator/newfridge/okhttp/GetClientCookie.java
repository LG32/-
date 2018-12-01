package com.example.administrator.newfridge.okhttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * @author RollingZ
 * cookie类，单例模式
 */
public class GetClientCookie {

    private static volatile OkHttpClient okHttpClient;

    public GetClientCookie( ){}

    public static OkHttpClient getHttpClient(){

        if( okHttpClient == null ){
            okHttpClient = new OkHttpClient.Builder()
                    .cookieJar ( new CookieJar () {
                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<> ();

                        @Override
                        public void saveFromResponse( HttpUrl url, List<Cookie> cookies ) {
                            cookieStore.put(url, cookies);
                            cookieStore.put(HttpUrl.parse("http://47.95.247.236:8080/"), cookies);
                            for (Cookie cookie : cookies) {
                                System.out.println("cookie Name:" + cookie.name());
                                System.out.println("cookie Path:" + cookie.path ());
                            }
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(HttpUrl.parse("http://47.95.247.236:8080/"));
                            if (cookies == null) {
                                System.out.println("没加载到cookie");
                            }
                            return cookies != null ? cookies : new ArrayList<Cookie> ();
                        }
                    })
                    .build();
        }
        return okHttpClient;
    }
}