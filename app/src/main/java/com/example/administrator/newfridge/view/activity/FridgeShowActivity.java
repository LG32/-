package com.example.administrator.newfridge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.newfridge.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

/**
 * @author RollingZ
 * 管理冰箱
 */
public class FridgeShowActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton fridgeadd;
    private FloatingActionButton fridagedelect;
    private FloatingActionMenu mActionMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridgeshow);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fridgeadd = findViewById(R.id.fridgeaddAction);
        fridagedelect = findViewById(R.id.fridgeDelectAction);
        mActionMenu = findViewById(R.id.fridgeactionmenu);

        mActionMenu.setVisibility(View.VISIBLE);
        fridgeadd.setOnClickListener(this);
        fridagedelect.setOnClickListener(this);
    }

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.fridgeaddAction:
                mActionMenu.close(true);
                Intent intent = new Intent();
                intent.setClass(FridgeShowActivity.this, FridgeActivity.class);
                startActivity(intent);
                break;
            case R.id.fridgeDelectAction:
                mActionMenu.close(true);
                Toast.makeText(this, "删除冰箱", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
