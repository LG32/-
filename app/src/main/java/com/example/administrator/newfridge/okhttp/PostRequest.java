package com.example.administrator.newfridge.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.newfridge.tool.MsgManager;
import com.example.administrator.newfridge.tool.MyHandlerMsg;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


class PostRequest implements MyHandlerMsg{

    private RequestBody requestBody;
    private String key;
    private String url;
    private String TAG;
    private int msgNum;
    private Handler myHandler;
    private UrlMap urlMap = new UrlMap ();
    private String result;
    private Context context;
    private SharedPreferences sharedPreferences;


    /**
     * @author LG32
     * 对于有返回cookie，不携带cookie
     * @param requestBody 请求体
     * @param key 获取url的key
     * @param myHandler 页面的handler对象
     * @param sharedPreferences 读取本地信息
     */
    PostRequest(RequestBody requestBody, String key, Handler myHandler, SharedPreferences sharedPreferences){
        this.requestBody = requestBody;
        this.myHandler = myHandler;
        this.key = key;
        this.sharedPreferences = sharedPreferences;

        TAG = key + "Request";
        Log.i ( TAG, "PostRequest key: " + key );
        setUrl ();
        setMsg();
        urlPostRequest ();
    }

    PostRequest(RequestBody requestBody, String key, Handler myHandler, Context context){
        this.requestBody = requestBody;
        this.myHandler = myHandler;
        this.key = key;
        this.context = context;
        TAG = key + "Request";
        Log.i ( TAG, "PostRequest key: " + key );
        setUrl ();
        setMsg();
        urlPostRequest ();
    }

    /**
     * @author LG32
     * 没cookie
     * @param requestBody 请求体
     * @param key 获取url的key
     * @param myHandler 页面的handler对象
     */
    PostRequest( RequestBody requestBody, String key, Handler myHandler ){
        this.requestBody = requestBody;
        this.myHandler = myHandler;
        this.key = key;

        TAG = key + "Request";
        Log.i ( TAG, "PostRequest key: " + key );
        setUrl ();
        setMsg();
        noCookieRequest ();
    }

    private void setUrl(){
        url = urlMap.getUrl ( key );
        Log.i ( TAG, "set url" + url );
    }

    private void setMsg(){
        MsgManager msgManager = new MsgManager ();
        msgNum = msgManager.getMsg ( key );
    }

    private void urlPostRequest(){

        OkHttpClient client = GetClientCookie.getHttpClient (sharedPreferences);

        Request request = new Request.Builder()
                .url(url)
                .post ( requestBody )
                .build();

        httpRequest ( client, request );
    }

    private void noCookieRequest(){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post ( requestBody )
                .build();

        httpRequest ( client, request );
    }

    private void httpRequest(OkHttpClient client, Request request){
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
                    result = response.body().string();
                    Log.i ( TAG, "返回值" + result );
                    Message msg = new Message ();
                    msg.what = msgNum;
                    msg.obj = result;
                    myHandler.sendMessage ( msg );
                }
            }
        });
    }


}
