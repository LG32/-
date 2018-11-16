package com.example.administrator.newfridge;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.newfridge.family.FamilyActivity;
import com.example.administrator.newfridge.food.FoodActivity;
import com.example.administrator.newfridge.foodmenu.FoodMenuActivity;
import com.example.administrator.newfridge.fridgemanager.FridgeActivity;
import com.example.administrator.newfridge.fridgemanager.FridgeShowActivity;
import com.example.administrator.newfridge.me.MeActivity;
import com.example.administrator.newfridge.record.RecordActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton food;
    private ImageButton foodshow;
    private ImageButton health;
    private ImageButton me;
    private ImageButton fridge;
    private ImageButton family;

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initview();
    }

    /**
     * 初始化布局
     */

    public void initview() {
        food = findViewById(R.id.foodmanager);
        foodshow = findViewById(R.id.foodshowmanager);
        health = findViewById(R.id.healthmanager);
        me = findViewById(R.id.Myinformation);
        fridge = findViewById(R.id.fridgeinformation);
        family = findViewById(R.id.familyinformation);

        food.setOnClickListener(this);
        foodshow.setOnClickListener(this);
        health.setOnClickListener(this);
        me.setOnClickListener(this);
        fridge.setOnClickListener(this);
        family.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foodmanager:
                final String[] items = new String[]{"小小冰箱", "中中冰箱", "大大冰箱"};//创建item
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("请选择冰箱")
                        .setIcon(R.mipmap.ic_launcher)
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {//添加单选框
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                index = i;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "这是确定按钮" + "点的是：" + items[index], Toast.LENGTH_SHORT).show();
                                Intent intentone = new Intent();
                                intentone.setClass(MainActivity.this, FoodActivity.class);
                                intentone.putExtra("fridgename", items[index]);
                                startActivity(intentone);
                            }
                        })

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "这是取消按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
                break;
            case R.id.foodshowmanager:
                Intent intenttwo = new Intent();
                intenttwo.setClass(MainActivity.this, FoodMenuActivity.class);
                startActivity(intenttwo);
                break;
            case R.id.healthmanager:
                Intent intentthree = new Intent();
                intentthree.setClass(MainActivity.this, RecordActivity.class);
                startActivity(intentthree);
                break;
            case R.id.Myinformation:
                Intent intentfour = new Intent();
                intentfour.setClass(MainActivity.this, MeActivity.class);
                startActivity(intentfour);
                break;
            case R.id.fridgeinformation:
                Intent intentfive = new Intent();
                intentfive.setClass(MainActivity.this, FridgeShowActivity.class);
                startActivity(intentfive);
                break;
            case R.id.familyinformation:
                Intent intentsix = new Intent();
                intentsix.setClass(MainActivity.this, FamilyActivity.class);
                startActivity(intentsix);
                break;
        }
    }
}
