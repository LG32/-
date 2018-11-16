package com.example.administrator.newfridge.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.newfridge.R;


public class RecordActivity extends AppCompatActivity {
    private AbilityMapView abilitymapview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        abilitymapview = findViewById(R.id.ability_map_view);
        abilitymapview.setData(new AbilityBean(80, 60, 60, 70, 80));
    }
}