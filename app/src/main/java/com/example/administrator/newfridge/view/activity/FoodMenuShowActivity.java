package com.example.administrator.newfridge.view.activity;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.model.MenuInfoBean;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author LG32
 * 食谱信息页面
 */
@SuppressLint("Registered")
public class FoodMenuShowActivity extends AppCompatActivity {

    private int fake_src;
    private MenuInfoBean menuInfoBean;
    private ArrayList<String> food_material = new ArrayList<> ();
    private ArrayList<String> cooking_way = new ArrayList<> ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.happy_cook);
        initValue ();
        initView();
    }

    private void initValue(){
        menuInfoBean = new MenuInfoBean ();
        String chief_material = "主料：五花肉";
        String second_material = "辅料：玉米油适量、香叶1片、八角1瓣、桂皮2块、香葱2根、生姜4片、食盐1小勺、料酒1汤匙、酱油2汤匙、白糖3汤匙、香醋4汤匙、清水5汤匙";

        fake_src = R.drawable.wuhuarou;
        menuInfoBean.setMenu_name ( "糖醋五花肉" );
        food_material.add ( chief_material );
        food_material.add ( second_material );
        menuInfoBean.setFood_material ( food_material );
        cooking_way.add ( "1.备好食材" );
        cooking_way.add ( "2.将五花肉洗净" );
        cooking_way.add ( "3.稍冷冻一会切块备用" );
        cooking_way.add ( "4.此时来准备调糖醋汁，碗中倒入：1汤匙料酒、2汤匙酱油、3汤匙白糖、4汤匙香醋、5汤匙清水，搅拌均匀" );
        cooking_way.add ( "5.锅热倒油，爆香葱姜调料后盛起，留底油放入五花肉" );
        cooking_way.add ( "6.煸至两面焦黄，重新倒入调料和少许温水，加盖小火煮5~8分钟左右" );
        cooking_way.add ( "7.待水汁不多时倒入刚调好的糖醋汁，加盖大火" );
        cooking_way.add ( "8.滚开转小火、炖至五花肉熟烂时转大火收汁，加食盐调味，炒匀后关火出锅" );
        menuInfoBean.setCooking_way ( cooking_way );
    }

    private void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView iv_food = findViewById ( R.id.iv_food );
        TextView tv_food_name = findViewById ( R.id.tv_food_name );
        TextView tv_chief_material = findViewById ( R.id.tv_chief_material );
        TextView tv_second_material = findViewById ( R.id.tv_second_material );
        TextView tv_cooking_way = findViewById ( R.id.tv_cooking_way );

        try{
            @SuppressLint("ResourceType")
            InputStream is = getResources().openRawResource(fake_src);
            Bitmap bitmap = BitmapFactory.decodeStream ( is );
            iv_food.setImageBitmap ( bitmap );
        }catch (Exception e){
            e.printStackTrace ();
        }

        tv_food_name.setText ( menuInfoBean.getMenu_name () );
        tv_chief_material.setText ( food_material.get ( 0 ) );
        tv_second_material.setText ( food_material.get ( 1 ) );

        String str = "";
        for (int i = 0; i < cooking_way.size (); i++){
            str = str + cooking_way.get ( i ) + "\n";
        }
        tv_cooking_way.setText ( str );
    }
}
