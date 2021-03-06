package com.example.administrator.newfridge.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.newfridge.R;
import com.example.administrator.newfridge.model.foodmodel.FoodBean;
import com.example.administrator.newfridge.model.foodmodel.FoodList;
import com.example.administrator.newfridge.model.IceBoxIdBean;
import com.example.administrator.newfridge.okhttp.GetBoxIdRequest;
import com.example.administrator.newfridge.okhttp.GetFoodListRq;
import com.example.administrator.newfridge.tool.JsonTool;
import com.example.administrator.newfridge.tool.MyHandlerMsg;
import com.example.administrator.newfridge.tool.nfc.NfcActivity;
import com.example.administrator.newfridge.tool.qrcode.QRScannerActivity;
import com.example.administrator.newfridge.view.adapter.EmptyRecyclerView;
import com.example.administrator.newfridge.view.adapter.FoodAdapter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author RollingZ
 * 主页面菜单
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private static final String TAG = "MainActivity";
    private FloatingActionMenu mActionMenu;
    protected EmptyRecyclerView mRecyclerView;
    private View mEmptyView;
    protected FoodAdapter mFoodAdapter;
    protected ArrayList<FoodBean> mList = new ArrayList<> (  );
    private MainHandler mainHandler = new MainHandler ();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        mDrawerLayout = findViewById ( R.id.drawer_layout );
        navView = findViewById ( R.id.nav_view );

        ActionBar actionBar = getSupportActionBar ();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled ( true );
            actionBar.setHomeAsUpIndicator ( R.drawable.ic_menu );
        }


        initView ();
        getBoxId();
        onNavViewClick ();
    }

    /**
     * 初始化布局
     */
    public void initView() {

        ImageButton foodshow = findViewById ( R.id.foodshowmanager );
        ImageButton health = findViewById ( R.id.healthmanager );
        ImageButton fridge = findViewById ( R.id.fridgeinformation );

        FloatingActionButton mItemLinearlayout = findViewById ( R.id.menu_item_linearlayout );
        FloatingActionButton mItemGridlayout = findViewById ( R.id.menu_item_gridlayout );
        FloatingActionButton mItemStaggeredlayout = findViewById ( R.id.menu_item_staggeredlayout );
        mActionMenu = findViewById ( R.id.actionmenu );
        mActionMenu.setVisibility ( View.VISIBLE );
        mItemLinearlayout.setOnClickListener ( this );
        mItemGridlayout.setOnClickListener ( this );
        mItemStaggeredlayout.setOnClickListener ( this );
        mRecyclerView = findViewById ( R.id.recyclerview );
        mEmptyView = findViewById ( R.id.empty_view );
        progressBar = new ProgressBar ( this );

        foodshow.setOnClickListener ( this );
        health.setOnClickListener ( this );
        fridge.setOnClickListener ( this );
    }

    /**
     * 配置RecyclerView
     */
    private void initData() {

        Log.i ( TAG, "initData: 初始化食物列表" );

        FoodList foodList = FoodList.getFoodList ();
        mList = foodList.getList ();

        mRecyclerView.setLayoutManager ( new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL, false ) );
        GridLayoutManager gridLayoutManager = new GridLayoutManager ( this, 2 );
        mRecyclerView.setLayoutManager ( gridLayoutManager );
        //设置适配器
        mFoodAdapter = new FoodAdapter ( this, mList );
        mRecyclerView.setAdapter ( mFoodAdapter );
        mRecyclerView.setmEmptyView ( mEmptyView );
        mRecyclerView.setNestedScrollingEnabled ( false );
        mRecyclerView.hideEmptyView ();

        mFoodAdapter.setOnFoodClickListener ( new FoodAdapter.OnFoodClickListener () {
            @Override
            public void onClick(int position, FoodBean foodBean) {
                Intent intent = new Intent ();
                intent.setClass ( MainActivity.this, FoodShowActivity.class );
                startActivity ( intent );
            }
        } );
    }

    private void onNavViewClick() {

        navView.setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener () {
            @SuppressLint("RtlHardcoded")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()) {
                    case R.id.nav_myInfo:
                        Intent myInfoIntent = new Intent ();
                        myInfoIntent.setClass ( MainActivity.this, MeInformation.class );
                        startActivity ( myInfoIntent );
                        break;

                    case R.id.nav_checkNew:
                        Log.i ( TAG, "onNavigationItemSelected: 检查更新" );
                        break;

                    case R.id.nav_menu:
                        Intent menuIntent = new Intent ();
                        menuIntent.setClass ( MainActivity.this, FoodMenuActivity.class );
                        startActivity ( menuIntent );
                        break;

                    case R.id.nav_family:
                        Intent familyIntent = new Intent ();
                        familyIntent.setClass ( MainActivity.this, FamilyActivity.class );
                        startActivity ( familyIntent );
                        break;
                }
                mDrawerLayout.closeDrawer ( Gravity.LEFT );
                return true;
            }
        } );
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId ()) {
            case R.id.foodshowmanager:
                intent = new Intent ();
                intent.setClass ( MainActivity.this, FoodMenuActivity.class );
                startActivity ( intent );
                break;

            case R.id.healthmanager:
                intent = new Intent ();
                intent.setClass ( MainActivity.this, RecordActivity.class );
                startActivity ( intent );
                break;

            case R.id.fridgeinformation:
                intent = new Intent ();
                intent.setClass ( MainActivity.this, FridgeShowActivity.class );
                startActivity ( intent );
                break;

            case R.id.menu_item_linearlayout:
                mActionMenu.close ( true );
                intent = new Intent ();
                intent.setClass ( MainActivity.this, NfcActivity.class );
                startActivity ( intent );
                break;

            case R.id.menu_item_gridlayout:
                mActionMenu.close ( true );
                intent = new Intent ();
                intent.setClass ( MainActivity.this, QRScannerActivity.class );
                intent.putExtra ( "one", "123" );
                startActivity ( intent );
                break;

            case R.id.menu_item_staggeredlayout:
                mActionMenu.close ( true );
                break;

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer ( GravityCompat.START );
                break;
            default:
        }
        return true;
    }

    private void getBoxId(){

        openLoading();

        SharedPreferences sharedPreferences = getSharedPreferences ( "cookie",
                MODE_PRIVATE );
        String cookie = sharedPreferences.getString ( "cookie", "" );
        new GetBoxIdRequest ( mainHandler, cookie );
    }

    private void getFoodList(){
        SharedPreferences sharedPreferences = getSharedPreferences ( "cookie",
                MODE_PRIVATE );
        String cookie = sharedPreferences.getString ( "cookie", "" );

        IceBoxIdBean iceBoxIdBean = IceBoxIdBean.getIceBoxIdBean ();
        ArrayList<String> iceId = iceBoxIdBean.getIceId_list ();

        Log.i ( TAG, "getFoodList: " + iceId.get ( 1 ) );

        RequestBody requestBody = new FormBody.Builder ()
                .add ( "macip", iceId.get ( 0 ) )
                .build ();

        new GetFoodListRq ( requestBody, mainHandler, cookie );
    }

    @SuppressLint("HandlerLeak")
    private class MainHandler extends Handler implements MyHandlerMsg{

        private JsonTool jsonTool;

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REQUEST_FAIL:
                    Toast.makeText ( MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT )
                            .show ();
                    break;

                case GETICEID_SUCCESS:
                    jsonTool = new JsonTool ( msg.obj.toString (), GETICEID_SUCCESS );
                    judgement ( jsonTool.judgeMsg () );
                    break;

                case GETFOOD_SUCCESS:
                    jsonTool = new JsonTool ( msg.obj.toString (), GETFOOD_SUCCESS );
                    judgement ( jsonTool.judgeMsg () );
                    break;
            }
        }
    }

    private void judgement(String code){
        switch (code){
            case "0":
                getFoodList();
                closeLoading ();
                break;
            case "1":
                Toast.makeText ( MainActivity.this, "冰箱列表为空", Toast.LENGTH_SHORT )
                        .show ();
                closeLoading ();
                break;

            case "2":
                initData();
                break;

            case "3":
                Toast.makeText ( MainActivity.this, "食物列表为空", Toast.LENGTH_SHORT )
                        .show ();
                break;
        }
    }

    private void openLoading(){
        if (progressBar.getVisibility () == View.GONE){
            progressBar.setVisibility ( View.VISIBLE );
        }
    }

    private  void closeLoading(){
        if (progressBar.getVisibility () == View.VISIBLE){
            progressBar.setVisibility ( View.GONE );
        }
    }
}
