package com.example.administrator.newfridge.okhttp;

import android.os.Message;
import android.util.Log;

import com.example.administrator.newfridge.view.activity.RegisterActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterRequest {

    private static final String TAG = "RegisterRequest";
    private static final String URL = "http://47.95.247.236:8080/login/18846833759/123456";
    private String telephone;
    private String set_id_code;
    private String password;
    private String ill;
    private String sex;
    private RegisterActivity.RegisterHandler RegisterHandler;

    public RegisterRequest(Map<String, String> information, RegisterActivity.RegisterHandler RegisterHandler){
        this.telephone = information.get("telephone");
        this.set_id_code = information.get ( "set_id_code" );
        this.password = information.get ( "password" );
        this.ill = information.get ( "ill" );
        this.sex = information.get ( "sex" );
        this.RegisterHandler = RegisterHandler;
        urlRequest ( URL );
    }

    /**
     * 网络请求
     */
    private void urlRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("tel", telephone)
                .add("password", password)
                .add ( "set_id_code", set_id_code )
                .add ( "ill", ill )
                .add ( "sex", sex )
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post (requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback () {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i ( TAG, "onFailure: 网络请求失败" );
                Message msg = new Message ();
                msg.what = 1;
                RegisterHandler.sendMessage ( msg );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i ( TAG, "返回值" + result );
                    String flag = jsonToString ( result );
                    if (flag.equals ( "注册成功" )){
                        Message msg = new Message ();
                        msg.what = 0;
                        RegisterHandler.sendMessage ( msg );
                    }else{
                        Message msg = new Message ();
                        msg.what = 1;
                        RegisterHandler.sendMessage ( msg );
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
