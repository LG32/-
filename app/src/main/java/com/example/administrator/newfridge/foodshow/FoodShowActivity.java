package com.example.administrator.newfridge.foodshow;


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

import java.io.InputStream;

public class FoodShowActivity extends AppCompatActivity {

    private FoodInfoBean foodInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodshow);
        initValue ();
        initView ();
    }

    private void initValue(){
        foodInfoBean = new FoodInfoBean ();

//        foodInfoBean.setFood_src (  );
        foodInfoBean.setFood_name ( "鸡腿肉" );
        foodInfoBean.setFood_description ( "     简介：鸡肉肉质细嫩，滋味鲜美，由于其味较淡，因此可使用于各种料理中。蛋白质的含量颇多，在肉之中，可以说是蛋白质最高的肉类之一，是属于高蛋白低脂肪的食品。钾硫酸氨基酸的含量颇多，因此可弥补牛及猪肉的不足。同时也由于鸡肉比其他肉类的维生素A含量多，而在量方面虽比蔬菜或肝脏差，但和牛肉和猪肉相比，其维生素A的含量却高出许多。 腿肉是从脚到腿的部位，及腿根一带的肉，其肉质颇坚硬，连皮一起摄取时，脂肪的含量较多，也是在整只鸡中铁分含量最多的一部分。" );
        foodInfoBean.setFood_begin ( "存放日期：2018-10-22" );
        foodInfoBean.setFood_end ( "保鲜到期日期：2018-10-25" );
        foodInfoBean.setFood_deadline ( "冷冻到期日期：2018-11-8" );
    }

    private void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView iv_food = findViewById ( R.id.iv_food );
        TextView tv_food_name = findViewById ( R.id.tv_food_name );
        TextView tv_food_description = findViewById ( R.id.tv_food_description );
        TextView tv_food_begin = findViewById ( R.id.tv_food_begin );
        TextView tv_food_end = findViewById ( R.id.tv_food_end );
        TextView tv_food_deadline = findViewById ( R.id.tv_food_deadline );

        try{
            int fake_src = R.drawable.chicken;
            @SuppressLint("ResourceType")
            InputStream is = getResources().openRawResource( fake_src );
            Bitmap bitmap = BitmapFactory.decodeStream ( is );
            iv_food.setImageBitmap ( bitmap );
        }catch (Exception e){
            e.printStackTrace ();
        }
        tv_food_name.setText ( foodInfoBean.getFood_name () );
        tv_food_description.setText ( foodInfoBean.getFood_description () );
        tv_food_begin.setText ( foodInfoBean.getFood_begin () );
        tv_food_end.setText ( foodInfoBean.getFood_end () );
        tv_food_deadline.setText ( foodInfoBean.getFood_deadline () );
    }
}
