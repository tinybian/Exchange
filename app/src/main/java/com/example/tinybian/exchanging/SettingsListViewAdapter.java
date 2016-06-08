package com.example.tinybian.exchanging;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/30.
 */
public class SettingsListViewAdapter extends BaseAdapter {
    ArrayList<SettingsListItemClass> listItem;//存放所有列表元素
    Activity activity;

    public SettingsListViewAdapter(ArrayList<SettingsListItemClass> listItem,Activity activity){
        this.listItem = listItem;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listItem != null ? listItem.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if(listItem!=null && position < listItem.size()){
            return listItem.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(listItem!=null && position < listItem.size()){
            return listItem.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return SettingsActivity.TYPE_SETTING_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        switch (type){
            case SettingsActivity.TYPE_SETTING_CLASSIFY:
                ViewHolderSettingsClassification holderSettingsClassification;

                if(convertView == null){//如果上次加载的页面为Null
                    //重新加载页面
                    convertView = activity.getLayoutInflater().inflate(R.layout.itemview_settings_list_classification, null);
                    //创建holder实例 并初始化其中的控件
                    holderSettingsClassification = new ViewHolderSettingsClassification();
                    holderSettingsClassification.textView = (TextView)convertView.findViewById(R.id.textview_settings_classification);
                    //将holder关联到view
                    convertView.setTag(holderSettingsClassification);
                }
                else {//若上次加载的页面还存在 则继续使用上次的
                    holderSettingsClassification = (ViewHolderSettingsClassification)convertView.getTag();
                }
                //设置控件内容
                holderSettingsClassification.textView.setText(listItem.get(position).getName());
                break;
            case SettingsActivity.TYPE_SETTING_ITEM:
                ViewHolderSettingsItem holderSettingsItem;
                if(convertView == null){
                    convertView = activity.getLayoutInflater().inflate(R.layout.itemview_setting_list_text, null);
                    holderSettingsItem = new ViewHolderSettingsItem();
                    holderSettingsItem.textView = (TextView)convertView.findViewById(R.id.textview_settings_item);
                    convertView.setTag(holderSettingsItem);
                }
                else{
                    holderSettingsItem = (ViewHolderSettingsItem)convertView.getTag();
                }
                holderSettingsItem.textView.setText(listItem.get(position).getName());
                break;
            default:
                break;
        }

        return convertView;
    }

    class ViewHolderSettingsClassification{
        TextView textView;
    }

    class ViewHolderSettingsItem{
        TextView textView;
    }
}
