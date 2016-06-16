package com.example.tinybian.exchanging;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/11/23.
 */
public class ClassificationFragment extends Fragment {
    ArrayList<ImageView> adsImageList = new ArrayList<>();
    ArrayList<ClassificationListItemClass> clsItemList;
    public static final int TYPE_CLASSIFICATION_ADS = 0;
    public static final int TYPE_CLASSIFICATION_ITEM = 1;
    public static final int TYPE_CLASSIFICATION_TITLE = 2;
    public static final int TYPE_CLASSIFICATION_COUNT = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.page_classification, null, false);

        loadImageView();//加载图片资源

        clsItemList = new ArrayList<>();//加载item资源
        initData();
        //Log.d("ImageBefore", adsImageList.toString());

        ListView classifyList = (ListView)rootview.findViewById(R.id.classify_listview);
        classifyList.setAdapter(new ClassificationPagerAdapter(clsItemList, this.getActivity()));

        return rootview;
    }

    private void loadImageView(){
        LayoutInflater inflater = LayoutInflater.from(this.getActivity());
        int[] imageRid = new int[]{ R.drawable.image1, R.drawable.image2, R.drawable.image3 };

        for(int i = 0; i < imageRid.length ; i++){
            ImageView imageView = (ImageView) inflater.inflate(R.layout.itemview_ads_image, null);
            imageView.setBackgroundResource(imageRid[i]);
            adsImageList.add(imageView);
        }
    }

    private void initData(){
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ADS,adsImageList));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_TITLE, "分类列表"));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification1, "地中海", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification2, "伏尔加河", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification3, "喜马拉雅", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification4, "西湖", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification5, "阿尔卑斯山", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification6, "昆仑山", "XXXXXXXXXXXXXXX")));
        clsItemList.add(new ClassificationListItemClass(TYPE_CLASSIFICATION_ITEM,
                new ClassifyItemClass(R.drawable.classification7, "潘帕斯", "XXXXXXXXXXXXXXX")));
    }

}

