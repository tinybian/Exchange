package com.example.tinybian.exchanging;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/29.
 */
public class MainSearchActivity extends AppCompatActivity {
    Toolbar tb;//标题栏
    TabLayout tl;//搜索结果TabLayout
    ViewPager vp;//搜索结果ViewPagaer
    ArrayList<String> tabListName = new ArrayList<String>();
    ArrayList<Fragment> tabFragmentList = new ArrayList<Fragment>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        /*设置toolbar*/
        tb = (Toolbar)findViewById(R.id.search_toolbar);
        tb.setNavigationIcon(R.drawable.ic_ab_drawer);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainSearchActivity.this.finish();
            }
        });

        Button searchBtn = (Button)findViewById(R.id.search_button);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initResultLayout();//加载显示结果的viewpager
            }
        });
    }

    private void initResultLayout(){
        /*设置ViewPager*/
        initViewpager();

        vp = (ViewPager) findViewById(R.id.search_viewpager);
        vp.setAdapter(new SearchFragmentPagerAdapter(getSupportFragmentManager(), tabListName, tabFragmentList));

        /*设置tab*/
        tl = (TabLayout)findViewById(R.id.search_tablayout);
        tl.setupWithViewPager(vp);

        /*改变tab中的字体
        * 因为TabLayout中不能通过xml直接修改字体
        * 获取每个Tab中的TextView,并修改字体*/
        setTabListTextStyle();
    }

    private void setTabListTextStyle(){
        ViewGroup vg = (ViewGroup)tl.getChildAt(0);//获取TabLayout
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

    private void initViewpager(){
        tabListName.add("商品");
        tabListName.add("店主");

        tabFragmentList.add(new SearchProductsFragment());
        tabFragmentList.add(new SearchPersonFragment());
    }

}
