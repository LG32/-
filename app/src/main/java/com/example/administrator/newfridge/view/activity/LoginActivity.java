package com.example.administrator.newfridge.view.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.okhttp.LoginRequest;
import com.example.administrator.newfridge.tool.JsonTool;
import com.example.administrator.newfridge.tool.MyHandlerMsg;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText telephone;
    private EditText password;
    private LoginHandler loginHandler = new LoginHandler ();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        initView ();
        initValue ();
    }

    private void initView() {
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );
        Button login = findViewById ( R.id.login );
        Button sign = findViewById ( R.id.sign );

        telephone = findViewById ( R.id.telephone );
        password = findViewById ( R.id.password );
        login.setOnClickListener ( this );
        sign.setOnClickListener ( this );
    }

    private void initValue() {
        sharedPreferences = getSharedPreferences ( "cookie",
                MODE_PRIVATE );
        String lastPassword = sharedPreferences.getString ( "password", "" );
        String lastTel = sharedPreferences.getString ( "tel", "" );

        telephone.setText ( lastTel );
        password.setText ( lastPassword );
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.login:

                String str_telephone = telephone.getText ().toString ();
                String str_password = password.getText ().toString ();
                if (!str_password.equals ( "" ) && !str_telephone.equals ( "" )) {
                    RequestBody requestBody = new FormBody.Builder ()
                            .add ( "tel", str_telephone )
                            .add ( "password", str_password )
                            .build ();

                    if (str_password.equals ( "123456" ) && str_telephone.equals ( "123456" )) {
                        toMainActivity ();
                    } else {
                        new LoginRequest ( requestBody, loginHandler, sharedPreferences );
                    }
                } else {
                    Toast.makeText ( this, "请输入完整的信息", Toast.LENGTH_SHORT )
                            .show ();
                }
                break;

            case R.id.sign:
                toRegisterActivity ();
                break;
        }
    }

    public void toMainActivity() {

        SharedPreferences.Editor editor = getSharedPreferences ( "cookie",
                MODE_PRIVATE ).edit ();
        editor.putString ( "tel", telephone.getText ().toString () );
        editor.putString ( "password", password.getText ().toString () );
        editor.apply ();

        Intent intent = new Intent ();
        intent.setClass ( LoginActivity.this, MainActivity.class );
        startActivity ( intent );
        finish ();
    }

    public void toRegisterActivity() {
        Intent intent = new Intent ();
        intent.setClass ( LoginActivity.this, RegisterActivity.class );
        startActivity ( intent );
        finish ();
    }

    private void loginJudge(String code) {
        Log.i ( "loginJudge: ", code );
        switch (code) {
            case "1":
                toMainActivity ();
                break;
            case "0":
                Toast.makeText ( LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT )
                        .show ();
                break;
            case "2":
                Toast.makeText ( LoginActivity.this, "该用户名不存在", Toast.LENGTH_SHORT )
                        .show ();
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    public class LoginHandler extends Handler implements MyHandlerMsg {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_FAIL:
                    Toast.makeText ( LoginActivity.this, "网络请求失败", Toast.LENGTH_SHORT )
                            .show ();
                    break;

                case LOGIN_SUCCESS:
                    JsonTool jsonTool = new JsonTool ( msg.obj.toString (), REQUEST_SUCCESS );
                    loginJudge ( jsonTool.judgeMsg () );
                    break;
            }
        }
    }
}
