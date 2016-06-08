package com.example.tinybian.exchanging;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/22.
 */
public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentList;
    ArrayList<String> listName;
    public MainFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, ArrayList<String> listName) {
        super(fm);
        this.listName = listName;
        this.fragmentList = fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return listName.get(position);
//    }

}
