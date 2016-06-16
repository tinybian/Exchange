package com.example.tinybian.exchanging;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/12/1.
 */
public class ClassificationPagerAdapter extends BaseAdapter {
    private ArrayList<ClassificationListItemClass> clsItemList;
    private Context context;

    private int currentItem = 150;
    public AdsImageHandler adsImageHandler;
    public ViewHolderAdsImage holderAdsImage;//定义在此处 供局部中的内部类访问

    public ClassificationPagerAdapter(ArrayList<ClassificationListItemClass> clsItemList, Activity activity){
        this.clsItemList = clsItemList;
        this.context = activity;
        adsImageHandler = new AdsImageHandler(this, currentItem);//处理广告轮播
    }

    @Override
    public int getCount() {
        return clsItemList != null ? clsItemList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        if(clsItemList!=null && position < clsItemList.size()){
            return clsItemList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(clsItemList!=null && position < clsItemList.size()){
            return clsItemList.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return ClassificationFragment.TYPE_CLASSIFICATION_COUNT;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        switch (type){
            case ClassificationFragment.TYPE_CLASSIFICATION_ADS://广告窗
                //Log.d("getView", "executed " + "position:" + Integer.toString(position));

                if(convertView == null)//避免以下代码在每次getView时都执行
                {
                    //Log.d("getViewInit", "executed");
                    convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.itemview_classification_adsviewpager,null);

                    ArrayList<ImageView> imageList = (ArrayList<ImageView>) clsItemList.get(position).getItem();
                    holderAdsImage = new ViewHolderAdsImage();

                    /*设置viewpager*/
                    holderAdsImage.vp = (ViewPager)convertView.findViewById(R.id.ads_viewpager);
                    holderAdsImage.vp.setAdapter(new AdsImageAdapter(imageList));
                    holderAdsImage.vp.setCurrentItem(currentItem);//设置当前图片

                        /*轮播的设置*/
                    holderAdsImage.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {
                            //Log.d("<<onPageSelected>>", "position="+Integer.toString(position));

                            //用户手动改变页面后 发送换页通知请求
                            adsImageHandler.sendMessage(Message.obtain(adsImageHandler, adsImageHandler.MSG_PAGE_CHANGED, position, 0));
                        }

                        @Override//不对页面滑动时的效果进行修改
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            //Log.d("<<ScrollStateChanged>>", "state="+Integer.toString(state));
                            switch (state) {
                                case ViewPager.SCROLL_STATE_DRAGGING:
                                    //Log.d("SCROLL_STATE_DRAGGING", "invoked");
                                    adsImageHandler.sendEmptyMessage(adsImageHandler.MSG_KEEP_SILENT);
                                    break;
                                case ViewPager.SCROLL_STATE_IDLE:
                                    //空闲时发出换页请求
                                    adsImageHandler.sendEmptyMessage(adsImageHandler.MSG_UPDATE_IMAGE);
                                    holderAdsImage.vp.setCurrentItem(currentItem++);
                                    break;

                                case ViewPager.SCROLL_STATE_SETTLING:

                                    holderAdsImage.vp.setCurrentItem(currentItem++);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }

                    /*设置广告框高度为宽度一半*/
                ViewTreeObserver vto = holderAdsImage.vp.getViewTreeObserver();
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        //Log.d("Globallayoutlistener", "called");
                        holderAdsImage.vp.getViewTreeObserver().removeOnPreDrawListener(this);//如果已经有 先remove
                        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) holderAdsImage.vp.getLayoutParams(); // 取控件vp当前的布局参数
                        linearParams.height = holderAdsImage.vp.getWidth() / 2;
                        holderAdsImage.vp.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件vp
                        return true;
                    }
                });

                    /*启动轮播*/
                adsImageHandler.sendEmptyMessage(adsImageHandler.MSG_BREAK_SILENT);

                break;
            case ClassificationFragment.TYPE_CLASSIFICATION_ITEM:
                ViewHolderClsItem holderClsItem;

                if(convertView == null){
                    convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.itemview_classification, null);
                    holderClsItem = new ViewHolderClsItem();
                    holderClsItem.itemLinearLayout = (LinearLayout) convertView.findViewById(R.id.cls_item_linearlayout);
                    holderClsItem.infoLinearLayout = (LinearLayout) convertView.findViewById(R.id.cls_info_linearlayout);
                    holderClsItem.imageView = (ImageView) convertView.findViewById(R.id.class_image);
                    holderClsItem.textTitle = (TextView) convertView.findViewById(R.id.class_title);
                    holderClsItem.textInfo = (TextView) convertView.findViewById(R.id.class_info);
                    convertView.setTag(holderClsItem);
                }
                else {
                    holderClsItem = (ViewHolderClsItem) convertView.getTag();
                }

                /*添加边距*/
                LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams)holderClsItem.itemLinearLayout.getLayoutParams();
                params2.setMargins(UtilClass.dpToPx(context, 10),UtilClass.dpToPx(context, 10),UtilClass.dpToPx(context, 10),0);
                /*最后一个Item要加载底部边距*/
                if(position == clsItemList.size()-1){
                    params2.bottomMargin = UtilClass.dpToPx(context, 10);
                }
                holderClsItem.itemLinearLayout.setLayoutParams(params2);


                /*修改宽度*/
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holderClsItem.infoLinearLayout.getLayoutParams();
                Point size = new Point();
                ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
                int windowWidth = size.x;
                params.width = windowWidth - ((LinearLayout.LayoutParams) holderClsItem.itemLinearLayout.getLayoutParams()).leftMargin
                        - ((LinearLayout.LayoutParams) holderClsItem.itemLinearLayout.getLayoutParams()).rightMargin
                        - holderClsItem.imageView.getWidth();
                holderClsItem.infoLinearLayout.setLayoutParams(params);


                /*载入数据*/
                holderClsItem.imageView.setImageResource(((ClassifyItemClass)clsItemList.get(position).getItem()).getImage());
                holderClsItem.textTitle.setText(((ClassifyItemClass)clsItemList.get(position).getItem()).getClassifyName());
                holderClsItem.textInfo.setText(((ClassifyItemClass)clsItemList.get(position).getItem()).getClassifyInfo());

                break;
            case ClassificationFragment.TYPE_CLASSIFICATION_TITLE:
                ViewHolderClsTitle holderClsTitle;

                if(convertView == null){
                    convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.itemview_classification_title, null);
                    holderClsTitle = new ViewHolderClsTitle();
                    holderClsTitle.textView = (TextView) convertView.findViewById(R.id.cls_title);
                    convertView.setTag(holderClsTitle);
                }
                else{
                    holderClsTitle = (ViewHolderClsTitle) convertView.getTag();
                }

                holderClsTitle.textView.setText((String)clsItemList.get(position).getItem());
                break;
            default:
                break;
        }

        return convertView;
    }

    static class ViewHolderAdsImage{
        ViewPager vp;
    }

    static class ViewHolderClsItem{
        LinearLayout itemLinearLayout;
        LinearLayout infoLinearLayout;
        ImageView imageView;
        TextView textTitle;
        TextView textInfo;
    }

    static class ViewHolderClsTitle{
        TextView textView;
    }

}
