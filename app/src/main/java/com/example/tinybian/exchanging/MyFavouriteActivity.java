package com.example.tinybian.exchanging;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/24.
 */
public class MyFavouriteActivity extends AppCompatActivity {
    Toolbar tb;//标题栏
    TabLayout tl;//列表结果TabLayout
    ViewPager vp;//列表结果ViewPagaer
    ArrayList<String> tabListName = new ArrayList<String>();//viewpager标签名
    ArrayList<Fragment> tabFragmentList = new ArrayList<Fragment>();//各标签页的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite);

        /*设置Toolbar*/
        tb = (Toolbar)findViewById(R.id.favourite_toolbar);
        if(tb!=null){
            setSupportActionBar(tb);
            tb.setNavigationIcon(R.drawable.ic_ab_drawer);//返回按钮
            getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏默认标题
            tb.setTitle("关注");
            tb.setTitleTextColor(getResources().getColor(R.color.colorBarText));
            tb.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyFavouriteActivity.this.finish();
                }
            });
        }

        initResultLayout();

    }

    private void initResultLayout(){
        /*加载标签名*/
        initViewpagerData();

        /*设置viewpager*/
        vp = (ViewPager) findViewById(R.id.favourite_viewpager);
        vp.setAdapter(new FavouriteFragmentPagerAdapter(getSupportFragmentManager(), tabListName, tabFragmentList));

        /*设置tab*/
        tl = (TabLayout)findViewById(R.id.favourite_tab);
        tl.setupWithViewPager(vp);

        /*改变tab中的字体
        * 因为TabLayout中不能通过xml直接修改字体
        * 获取每个Tab中的TextView,并修改字体*/
        setTabListTextStyle();
    }

    private void setTabListTextStyle(){
        ViewGroup vg = (ViewGroup)tl.getChildAt(0);//
        Log.d("TabLayout ChildAt(0)", vg.toString());
        int tabCount = vg.getChildCount();//tab数量
        for (int i = 0; i < tabCount;i++)
        {
            ViewGroup vgTab = (ViewGroup)vg.getChildAt(i);//获取每个tab
            int tabChildCount = vgTab.getChildCount();//tab中view的数量
            for(int j = 0;j < tabChildCount;j++)
            {
                View tabChild = vgTab.getChildAt(j);//获取tab
                if(tabChild instanceof TextView) {
                    ((TextView) tabChild).setTextSize(16);
                }
            }
        }
    }

    private void initViewpagerData(){
        tabListName.add("商品");
        tabListName.add("店主");

        tabFragmentList.add(new FavouriteProductsFragment());
        tabFragmentList.add(new FavouritePersonFragment());
    }

}
