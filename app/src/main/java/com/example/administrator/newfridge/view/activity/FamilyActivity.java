package com.example.administrator.newfridge.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.newfridge.tool.qrcode.QRScannerActivity;
import com.example.administrator.newfridge.R;

/**
 * @author RollingZ
 * 我的家庭
 */
public class FamilyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        toolbar = findViewById(R.id.toolbarfamily);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.test_menu:
                        Intent intent = new Intent();
                        intent.setClass(FamilyActivity.this, QRScannerActivity.class);
                        intent.putExtra("nametwo","two");
                        startActivity(intent);
                        //Toast.makeText(FamilyActivity.this,"nihao",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_family,menu);//加载menu布局
        return true;
    }
}
