package com.example.tinybian.exchanging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/24.
 */
public class SettingsActivity extends AppCompatActivity {

    public static final int TYPE_SETTING_CLASSIFY = 0;
    public static final int TYPE_SETTING_ITEM = 1;

    public static final int TYPE_SETTING_COUNT = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /*设置toolbar*/
        Toolbar tb = (Toolbar)findViewById(R.id.setting_toolbar);
        if(tb!=null)
        {
            setSupportActionBar(tb);
            tb.setNavigationIcon(R.drawable.ic_ab_drawer);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//隐藏toolbar默认标题
            tb.setTitle("设置");
            tb.setTitleTextColor(getResources().getColor(R.color.colorBarText));
            tb.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SettingsActivity.this.finish();
                }
            });
        }

        /*设置listview*/
            /*初始化各Item内容*/
        ArrayList<SettingsListItemClass> listItem = new ArrayList<SettingsListItemClass>();
        listItem.add(new SettingsListItemClass(TYPE_SETTING_CLASSIFY, "账户"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "修改密码"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "修改联系方式"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "退出登录"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_CLASSIFY, "应用"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "通知栏设置"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "给我们建议"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "检查更新"));
        listItem.add(new SettingsListItemClass(TYPE_SETTING_ITEM, "关于"));

        ListView lv = (ListView) findViewById(R.id.listview);
        SettingsListViewAdapter settingsListViewAdapter = new SettingsListViewAdapter(listItem, SettingsActivity.this);
        lv.setAdapter(settingsListViewAdapter);
    }
}
