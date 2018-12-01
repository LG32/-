package com.example.administrator.newfridge.okhttp;

import android.os.Message;
import android.util.Log;

import com.example.administrator.newfridge.view.activity.LoginActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//18846833759/123456
public class LoginRequest {
    private static final String TAG = "LoginRequest";
    private static final String URL = "http://47.95.247.236:8080/login/";
    private String telephone;
    private String password;
    private LoginActivity.LoginHandler loginHandler;
    private static final int LOGIN_SECCESS = 0;
    private static final int LOGIN_FAIL = 1;
    private static final int REQUEST_FAIL = 2;

    public LoginRequest(String telephone, String password, LoginActivity.LoginHandler loginHandler){
        this.telephone = telephone;
        this.password = password;
        this.loginHandler = loginHandler;
        urlRequest ( URL );
    }

    /**
     * 网络请求POST方法
     */
    private void urlRequest(String url) {

        OkHttpClient client = GetClientCookie.getHttpClient ();
        RequestBody requestBody = new FormBody.Builder()
                .add("tel", telephone)
                .add("password", password)
                .build();

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
                loginHandler.sendMessage ( msg );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    Log.i ( TAG, "返回值" + result );
                    String flag = jsonToString ( result );
                    if (flag.equals ( "登录成功" )){
                        Message msg = new Message ();
                        msg.what = LOGIN_SECCESS;
                        loginHandler.sendMessage ( msg );
                    }else{
                        Message msg = new Message ();
                        msg.what = LOGIN_FAIL;
                        loginHandler.sendMessage ( msg );
                    }
                }
            }
        });
    }

    private String jsonToString(String json){
        JsonObject jsonObject = (JsonObject) new JsonParser ().parse ( json );
        return jsonObject.get ( "msg" ).getAsString ();
    }
}
