package com.example.administrator.newfridge.okhttp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class PostRequest {

    private RequestBody requestBody;
    private String key;
    private String url;
    private String TAG;
    private Handler myHandler;

    private static final int REQUEST_FAIL = 0;

    public PostRequest(RequestBody requestBody, String key, Handler myHandler){
        this.requestBody = requestBody;
        this.key = key;
        this.myHandler = myHandler;
        TAG = key + "Request";

        setUrl ();
        urlRequest ();
    }

    private void setUrl(){
        UrlMap urlMap = new UrlMap ();
        url = urlMap.getUrl ( key );
    }

    private void urlRequest(){
        OkHttpClient client = GetClientCookie.getHttpClient ();

        Request request = new Request.Builder()
                .url(url)
                .post ( requestBody )
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback () {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i ( TAG, "onFailure: 网络请求失败" );
                Message msg = new Message ();
                msg.what = REQUEST_FAIL;
                myHandler.sendMessage ( msg );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i ( TAG, "返回值" + result );

                    judge();
                }
            }
        });
    }

    abstract void judge();
}
