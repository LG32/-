package com.example.administrator.newfridge.okhttp;

import android.content.SharedPreferences;
import android.os.Handler;
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
    private static final String KEY = "register";

    public RegisterRequest(RequestBody requestBody, Handler loginHandler){
        Log.i ( TAG, "开始注册请求！" );
        new PostRequest ( requestBody, KEY, loginHandler );
    }

}
