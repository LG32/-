package com.example.administrator.newfridge.view.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.okhttp.LoginRequest;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText telephone;
    private EditText password;
    private LoginHandler loginHandler = new LoginHandler ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView ();

    }

    private void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button login = findViewById ( R.id.login );
        Button sign = findViewById ( R.id.sign );

        telephone = findViewById ( R.id.telephone );
        password = findViewById ( R.id.password );
        login.setOnClickListener ( this );
        sign.setOnClickListener ( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()){
            case R.id.login:

                String str_telephone = telephone.getText ().toString ();
                String str_password = password.getText ().toString ();
                if (!str_password.equals ( "" ) && !str_telephone.equals ( "" )){
                    LoginRequest loginRequest = new LoginRequest (str_telephone, str_password, loginHandler);

                }
                if (str_password.equals ( "123" ) && str_telephone.equals ( "123" )){
                    toMainActivity ();
                }
                else {
                    Toast.makeText ( this, "请输入完整的信息", Toast.LENGTH_SHORT)
                            .show ();
                }
                break;

            case R.id.sign:
                toRegisterActivity();
                break;
        }
    }

    public void toMainActivity(){
        Intent intent =  new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void toRegisterActivity(){
        Intent intent = new Intent ();
        intent.setClass ( LoginActivity.this, RegisterActivity.class );
        startActivity ( intent );
    }

    @SuppressLint("HandlerLeak")
    public class LoginHandler extends Handler {

        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    toMainActivity();
                    break;
                case 1:
                    Toast.makeText ( LoginActivity.this, "账户不存在或密码错误", Toast.LENGTH_SHORT )
                    .show ();
                    break;
            }
        }
    }
}
