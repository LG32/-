package com.example.administrator.newfridge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.view.adapter.FoodAdapter;
import com.example.administrator.newfridge.model.foodmodel.FoodModel;
import com.example.administrator.newfridge.tool.nfc.NfcActivity;
import com.example.administrator.newfridge.tool.qrcode.QRScannerActivity;
import com.example.administrator.newfridge.view.adapter.EmptyRecyclerView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RollingZ
 * 食材管理
 */
public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionMenu mActionMenu;

    protected EmptyRecyclerView mRecyclerView;
    private View mEmptyView;
    protected FoodAdapter mFoodAdapter;
    protected List<FoodModel> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initToolbar();
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        FloatingActionButton mItemLinearlayout = findViewById ( R.id.menu_item_linearlayout );
        FloatingActionButton mItemGridlayout = findViewById ( R.id.menu_item_gridlayout );
        FloatingActionButton mItemStaggeredlayout = findViewById ( R.id.menu_item_staggeredlayout );
        mActionMenu = findViewById(R.id.actionmenu);

        mActionMenu.setVisibility(View.VISIBLE);
        mItemLinearlayout.setOnClickListener(this);
        mItemGridlayout.setOnClickListener(this);
        mItemStaggeredlayout.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.recyclerview);
        mEmptyView = findViewById(R.id.empty_view);

    }


    /**
     * 初始化Toolbar的属性
     */
    private void initToolbar() {
        Toolbar toolbar = findViewById ( R.id.toolbar );
        Intent intent = getIntent();//获取传来的intent对象
        String data = intent.getStringExtra("fridgename");
        toolbar.setTitle(data);
        setSupportActionBar( toolbar );
        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            TextView titleTextView = (TextView) f.get( toolbar );
            titleTextView.setClickable(true);
            titleTextView.setOnClickListener(new View.OnClickListener() {
                long firstTime = 0;
                @Override
                public void onClick(View v) {
                    //第一次点击的时间
                    long pressTime = System.currentTimeMillis();
                    //比较两次时间差
                    if (pressTime - firstTime > 500) {
                        firstTime = pressTime;
                        return;
                    }
                    mRecyclerView.smoothScrollToPosition(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置RecyclerView
     */
    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager ( this, 2 );
        mRecyclerView.setLayoutManager ( gridLayoutManager );
        //设置适配器
//        mFoodAdapter = new FoodAdapter(this, mList);
        mRecyclerView.setAdapter(mFoodAdapter);
        mRecyclerView.setmEmptyView(mEmptyView);
        mRecyclerView.hideEmptyView();
        FoodModel foodModel1 = new FoodModel(1, "牛眼肉", "存放时间：2018-10-18", "类别：肉类",R.drawable.beef);
        FoodModel foodModel2 = new FoodModel(2, "鲫鱼", "存放时间：2018-10-20", "类别：海鲜类",R.drawable.yu);
        FoodModel foodModel3 = new FoodModel(3, "圆葱", "存放时间：2018-10-21", "类别：蔬菜类",R.drawable.yuancong);
        FoodModel foodModel4 = new FoodModel(4, "鸡蛋", "存放时间：2018-10-21", "类别：蛋类",R.drawable.egg);
        FoodModel foodModel5 = new FoodModel(5, "鸡腿肉", "存放时间：2018-10-22", "类别：肉类",R.drawable.chicken);
        FoodModel foodModel6 = new FoodModel(6, "猪颈肉", "存放时间：2018-10-22", "类别：肉类",R.drawable.meet);
        FoodModel foodModel7 = new FoodModel(7, "香菜", "存放时间：2018-10-22", "类别：蔬菜类",R.drawable.xiangcai);
        FoodModel foodModel8 = new FoodModel(8, "羊肉", "存放时间：2018-10-20", "类别：肉类",R.drawable.yangrou);
        FoodModel foodModel9 = new FoodModel(9, "红辣椒", "存放时间：2018-10-18", "类别：蔬菜类",R.drawable.honglajiao);
        FoodModel foodModel10 = new FoodModel(10,"苹果","存放时间：2018-10-19", "类别：水果类",R.drawable.apple);
        mList.add(foodModel1);
        mList.add(foodModel2);
        mList.add(foodModel3);
        mList.add(foodModel4);
        mList.add(foodModel5);
        mList.add(foodModel6);
        mList.add(foodModel7);
        mList.add(foodModel8);
        mList.add(foodModel9);
        mList.add(foodModel10);
//        mFoodAdapter.setOnFoodClickListener(new FoodAdapter.OnFoodClickListener(){
//            @Override
//            public void onClick(int position, FoodModel foodModel) {
//                Intent intent = new Intent();
//                intent.setClass(FoodActivity.this,FoodShowActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.menu_item_linearlayout:
                mActionMenu.close(true);
                Intent intent = new Intent();
                intent.setClass(FoodActivity.this, NfcActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_gridlayout:
                mActionMenu.close(true);
                Intent intent2 = new Intent();
                intent2.setClass(FoodActivity.this, QRScannerActivity.class);
                intent2.putExtra("one", "123");
                startActivity(intent2);
                break;
            case R.id.menu_item_staggeredlayout:
                mActionMenu.close(true);
                break;
        }
    }
}
