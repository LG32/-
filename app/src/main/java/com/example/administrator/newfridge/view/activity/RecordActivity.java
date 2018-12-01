package com.example.administrator.newfridge.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.model.AbilityBean;
import com.example.administrator.newfridge.view.adapter.AbilityMapView;

/**
 * @author RollingZ
 * 健康建议activity
 */
public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AbilityMapView abilitymapview = findViewById ( R.id.ability_map_view );
        abilitymapview.setData(new AbilityBean (80, 60, 60, 70, 80));
    }
}