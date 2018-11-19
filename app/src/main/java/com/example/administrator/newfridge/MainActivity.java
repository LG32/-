package com.example.administrator.newfridge;

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
import com.example.administrator.newfridge.fridgemanager.FridgeShowActivity;
import com.example.administrator.newfridge.me.MeActivity;
import com.example.administrator.newfridge.record.RecordActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    /**
     * 初始化布局
     */
    public void initView() {
        ImageButton food = findViewById ( R.id.foodmanager );
        ImageButton foodshow = findViewById ( R.id.foodshowmanager );
        ImageButton health = findViewById ( R.id.healthmanager );
        ImageButton me = findViewById ( R.id.Myinformation );
        ImageButton fridge = findViewById ( R.id.fridgeinformation );
        ImageButton family = findViewById ( R.id.familyinformation );

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
                                Toast.makeText(MainActivity.this, "这是确定按钮" + "点的是："
                                        + items[index], Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent();
                                intent1.setClass(MainActivity.this, FoodActivity.class);
                                intent1.putExtra("fridgename", items[index]);
                                startActivity(intent1);
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
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, FoodMenuActivity.class);
                startActivity(intent2);
                break;
            case R.id.healthmanager:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, RecordActivity.class);
                startActivity(intent3);
                break;
            case R.id.Myinformation:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, MeActivity.class);
                startActivity(intent4);
                break;
            case R.id.fridgeinformation:
                Intent intent5 = new Intent();
                intent5.setClass(MainActivity.this, FridgeShowActivity.class);
                startActivity(intent5);
                break;
            case R.id.familyinformation:
                Intent intent6 = new Intent();
                intent6.setClass(MainActivity.this, FamilyActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
