package com.example.administrator.newfridge.view.activity;


import android.annotation.SuppressLint;
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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.newfridge.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LG32
 * 注册功能
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText telephone;
    private EditText set_id_code;
    private EditText password;
    private EditText ill;
    private EditText confirm_password;

    private String sex_str;
    private RegisterHandler handler = new RegisterHandler ();

    private static final int INFORMATION_OK = 0;//输入的信息ok
    private static final int INFORMATION_NULL = 1;//输入的信息有空的
    private static final int PASSWORD_MISS = 2;//输入的密码有误（两次输入不相同）
    private static final String TAG = "注册页面";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView ();
    }

    private void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        telephone = findViewById ( R.id.telephone );
        set_id_code = findViewById ( R.id.set_id_code );
        password = findViewById ( R.id.password );
        confirm_password = findViewById ( R.id.confirm_password );
        ill = findViewById ( R.id.ill );
        RadioGroup sex = findViewById ( R.id.sex );
        Button get_id_code = findViewById ( R.id.get_id_code );
        Button finish = findViewById ( R.id.finish );

        setSupportActionBar(toolbar);
        get_id_code.setOnClickListener ( this );
        finish.setOnClickListener ( this );

        sex.setOnCheckedChangeListener ( new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        sex_str = "男性";
                        break;
                    case R.id.female:
                        sex_str = "女性";
                        break;
                }
            }
        } );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()){
            case R.id.finish:
                Map<String, String> information = new HashMap<> ();
                information.put ( "telephone", telephone.getText ().toString () );
                information.put ( "set_id_code", set_id_code.getText ().toString () );
                information.put ( "password", password.getText ().toString () );
                information.put ( "confirm_password", confirm_password.getText ().toString () );
                information.put ( "ill", ill.getText ().toString () );
                information.put ( "sex", sex_str );
                switch ( judgeInformation (information) ){
                    case INFORMATION_NULL:
                        Toast.makeText ( this, "请输入完整的信息", Toast.LENGTH_SHORT).show ();
                        break;
                    case PASSWORD_MISS:
                        Toast.makeText ( this, "两次输入的密码不相同", Toast.LENGTH_SHORT ).show ();
                        break;
                    case INFORMATION_OK:
                        Log.i ( TAG, "信息正确" );

                        break;
                }
                break;

                case R.id.get_id_code:

                    break;
        }
    }

    private int judgeInformation(Map<String, String> information){
        for (Map.Entry<String, String> entry : information.entrySet()){
            if (entry.getValue () == null){
                return INFORMATION_NULL;
            }
        }
        if (information.get ( "password" ).equals ( information.get ( "confirm_password" ) )){
            return PASSWORD_MISS;
        }else {
            return INFORMATION_OK;
        }
    }

    @SuppressLint("HandlerLeak")
    public class RegisterHandler extends Handler{

        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:

                    break;
                case 1:
                    Toast.makeText ( RegisterActivity.this, "账户不存在或密码错误", Toast.LENGTH_SHORT )
                            .show ();
                    break;
            }
        }
    }
}
