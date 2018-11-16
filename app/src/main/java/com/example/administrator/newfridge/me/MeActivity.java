package com.example.administrator.newfridge.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.family.FamilyActivity;
import com.leon.lib.settingview.LSettingItem;


public class MeActivity extends AppCompatActivity {
    private LSettingItem mLSettingItemPerson;
    private LSettingItem mLSettingItemVersion;
    private LSettingItem mLSettingItemSave;
    private LSettingItem mLSettingItemfamily;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initview();
        initListener();
    }

    public void initview() {
        mLSettingItemPerson = findViewById(R.id.setting_person);
                mLSettingItemVersion =  findViewById(R.id.setting_version);
        mLSettingItemSave =  findViewById(R.id.setting_save);
        mLSettingItemfamily = findViewById(R.id.setting_family);
    }


    private void initListener() {

        mLSettingItemPerson.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent = new Intent();
                intent.setClass(MeActivity.this,Meinformation.class);
                startActivity(intent);
            }
        });

        mLSettingItemSave.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
               // startActivity(new Intent(mContext, CollectActivity.class));
            }
        });
        mLSettingItemfamily.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent = new Intent();
                intent.setClass(MeActivity.this,FamilyActivity.class);
                startActivity(intent);
            }
        });

    }
}
