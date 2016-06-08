package com.example.tinybian.exchanging;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/26.
 */
public class AdsImageAdapter extends PagerAdapter {
    private ArrayList<ImageView> adsImageList;

    public AdsImageAdapter(ArrayList<ImageView> adsImageList){
        this.adsImageList = adsImageList;
        //Log.d("ImageAfter", adsImageList.toString());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.d("AdsImageAdapter", "instantiateItem() executed----position:"+Integer.toString(position));

        int listSize = adsImageList.size();

        if(position<0)
            position = listSize + position % listSize;
        else
            position %= listSize;

        ImageView imageView = adsImageList.get(position);//获取到该ImageView
        ViewParent viewParent = imageView.getParent();//如果该view已有父组件， 先remove掉
        if(viewParent!=null) {
            ((ViewGroup)viewParent).removeView(imageView);
        }
        ((ViewPager)container).addView(imageView);//指定新的父组件
        return imageView;
    }
}
