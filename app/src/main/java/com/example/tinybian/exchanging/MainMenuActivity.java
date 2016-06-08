package com.example.tinybian.exchanging;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
    ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();//Toolbar上的Tab名

    ViewPager viewpager;
    TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*设置toolbar*/
        Toolbar tb = (Toolbar)findViewById(R.id.main_toolbar);
        if(tb != null) {
            setSupportActionBar(tb);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏toolbar默认标题
        }

        /*获取控件*/
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        tl = (TabLayout)findViewById(R.id.tabtitle);

        /*初始化相关操作*/
        init();

        /*设置viewpager的adapter 并将TAB名传给viewpager*/
        viewpager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, listName));

        /*tablayout的viewpager*/
        tl.setupWithViewPager(viewpager);

        /*设置Tab的Icon*/
        tl.getTabAt(0).setIcon(R.drawable.btn_tab1_index);
        tl.getTabAt(1).setIcon(R.drawable.btn_tab2_city);
        tl.getTabAt(2).setIcon(R.drawable.btn_tab3_message);
    }

    private void init(){
        /*初始化标题栏TAB名*/
        listName.add("Tab A");
        listName.add("Tab B");
        listName.add("Tab C");

        /*初始化Fragment*/
        fragmentList.add(new ClassificationFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MessageFragment());
    }

    /*关联toolbar的菜单栏*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return true;
    }

//    @Override
//    protected void onPause() {
//        TextView tv = (TextView)findViewById(R.id.test);
//        tv.setText("MainActivity onPause");
//        super.onPause();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_search:
                startActivity(new Intent(MainMenuActivity.this, MainSearchActivity.class));
                break;
            case R.id.action_settings_shop:
                startActivity(new Intent(MainMenuActivity.this, MyShopActivity.class));
                break;
            case R.id.action_settings_focus:
                startActivity(new Intent(MainMenuActivity.this, MyFavouriteActivity.class));
                break;
            case R.id.action_settings_order:
                startActivity(new Intent(MainMenuActivity.this, MyOrderActivity.class));
                break;
            case R.id.action_settings:
                startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
