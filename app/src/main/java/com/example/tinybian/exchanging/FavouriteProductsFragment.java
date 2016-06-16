package com.example.tinybian.exchanging;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/12/21.
 */
public class FavouriteProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<FavProductsItemClass> productsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.page_favourite_products, null);

        initData();
        recyclerView = (RecyclerView)rootView.findViewById(R.id.fav_product_list);
        recyclerView.addItemDecoration(new ItemDivider(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new FavouriteProductsAdapter(this.getContext(), productsList));

        return rootView;
    }

    private void initData(){
        productsList = new ArrayList<>();
        productsList.add(new FavProductsItemClass(R.drawable.product0, R.drawable.person0, "李雷", "韩梅梅", true));
        productsList.add(new FavProductsItemClass(R.drawable.product10, R.drawable.person1, "二少奶奶", "隔壁老王", true));
        productsList.add(new FavProductsItemClass(R.drawable.product2, R.drawable.person2, "徐严", "雪姨", false));
        productsList.add(new FavProductsItemClass(R.drawable.product3, R.drawable.person0, "奥巴马", "韩梅梅", true));
        productsList.add(new FavProductsItemClass(R.drawable.product4, R.drawable.person3, "韩红YJ", "广东老妈妈", false));
        productsList.add(new FavProductsItemClass(R.drawable.product5, R.drawable.person2, "安东尼克斯普利特", "雪姨", true));
        productsList.add(new FavProductsItemClass(R.drawable.product6, R.drawable.person3, "罗玄", "韩梅梅", true));
        productsList.add(new FavProductsItemClass(R.drawable.product7, R.drawable.person1, "隔壁老王媳妇", "隔壁老王", false));
    }
}
