package com.example.administrator.newfridge.foodmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.foodmenushow.FoodMenuShowActivity;
import com.example.administrator.newfridge.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class FoodMenuActivity extends AppCompatActivity {
    protected Context mContext;
    protected EmptyRecyclerView mRecyclerView;
    private View mEmptyView;
    protected FoodMenuAdapter mFoodAdapter;
    protected List<FoodMenu> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initview();
        initData();
    }

    public void initview() {
        mRecyclerView = findViewById(R.id.fragment_recyclerview1);
        mEmptyView = findViewById(R.id.empty_view1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //设置适配器
        mFoodAdapter = new FoodMenuAdapter(this, mList);
        mRecyclerView.setAdapter(mFoodAdapter);
        mRecyclerView.setmEmptyView(mEmptyView);
        mRecyclerView.hideEmptyView();
    }

        public void initData() {
        FoodMenu foodMenu = new FoodMenu(R.drawable.wuhuarou,"糖醋五花肉");
        FoodMenu foodMenu2 = new FoodMenu(R.drawable.jizhua,"秘制卤鸡爪");
        FoodMenu foodMenu3 = new FoodMenu(R.drawable.roupian,"蒜香肉片");
        mList.add(foodMenu);
        mList.add(foodMenu2);
        mList.add(foodMenu3);
        mFoodAdapter.setOnBaseClickListener(new FoodMenuAdapter.OnFoodClickListener(){
            @Override
            public void onClick(int position, FoodMenu FoodMenu) {
                Intent intent = new Intent();
                intent.setClass(FoodMenuActivity.this,FoodMenuShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
