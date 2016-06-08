package com.example.tinybian.exchanging;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/12/21.
 */
public class SearchFragmentPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> tabListName;
    ArrayList<Fragment> tabFragmentList;

    public SearchFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabListName, ArrayList<Fragment> tabFragmentList){
        super(fm);
        this.tabListName = tabListName;
        this.tabFragmentList = tabFragmentList;
    }

    @Override
    public int getCount() {
        return tabListName.size();
    }

    @Override
    public Fragment getItem(int position) {
        if(position<0 || position>=tabFragmentList.size())
            return null;
        return tabFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position<0 || position>=tabListName.size())
            return null;
        return tabListName.get(position);
    }
}
