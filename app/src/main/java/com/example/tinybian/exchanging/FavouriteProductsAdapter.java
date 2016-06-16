package com.example.tinybian.exchanging;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tinybian on 2015/12/22.
 */
public class FavouriteProductsAdapter extends RecyclerView.Adapter<FavouriteProductsAdapter.ProductsViewHolder> {
    private ArrayList<FavProductsItemClass> productsList;
    private LayoutInflater mInflater;
    private Context context;

    public FavouriteProductsAdapter(Context context, ArrayList<FavProductsItemClass> productsList){
        this.productsList = productsList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{
        public ProductsViewHolder(View convertView){
            super(convertView);//必须要传入显示的View
        }

        LinearLayout itemLinearLayout;
        LinearLayout infoLinearLayout;
        ImageView productImage;
        ImageView personImage;
        TextView productName;
        TextView personName;
        ImageView isPresent;
        Button cancelBtn;
    }

    /*创建ViewHolder*/
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_favourite_products, null);//加载List中的item布局

        if(view.getParent() == null){
            //Log.d("parent", parent.toString());
            ((RecyclerView)parent).addView(view);
        }

        ProductsViewHolder productsVH = new ProductsViewHolder(view);//新建viewholder并将Item布局传入

        productsVH.productImage = (ImageView)view.findViewById(R.id.fav_product_image);//加载viewholder中的各个组件
        productsVH.personImage = (ImageView)view.findViewById(R.id.fav_product_master_image);
        productsVH.productName = (TextView)view.findViewById(R.id.fav_product_name);
        productsVH.personName = (TextView)view.findViewById(R.id.fav_product_master_name);
        productsVH.isPresent = (ImageView)view.findViewById(R.id.fav_product_ispresent);
        productsVH.cancelBtn = (Button)view.findViewById(R.id.fav_product_cancel);
        productsVH.infoLinearLayout = (LinearLayout)view.findViewById(R.id.fav_product_info_linearlayout);
        productsVH.itemLinearLayout = (LinearLayout)view.findViewById(R.id.fav_product_linearlayout);

        /*修改宽度*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)productsVH.infoLinearLayout.getLayoutParams();
        int windowWidth = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        params.width = windowWidth - parent.getPaddingRight() - parent.getPaddingLeft() - productsVH.productImage.getWidth();
        productsVH.infoLinearLayout.setLayoutParams(params);

        /*添加外边距
        RecyclerView.LayoutParams params2 = (RecyclerView.LayoutParams)productsVH.itemLinearLayout.getLayoutParams();
        params2.topMargin = UtilClass.dpToPx(context, 10);
        productsVH.itemLinearLayout.setLayoutParams(params2);*/

        return productsVH;
    }

    /*绑定ViewHolder值*/
    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {

        if(productsList.size()>0){
            holder.productImage.setImageResource(productsList.get(position).getProductImage());
            holder.personImage.setImageResource(productsList.get(position).getPersonImage());
            holder.productName.setText(productsList.get(position).getProductName());
            holder.personName.setText(productsList.get(position).getPersonName());

            /*若是可赠送的商品显示赠送标签，否则不显示*/
            if(productsList.get(position).getIsPresent()){
                holder.isPresent.setVisibility(View.VISIBLE);
            }
            else {
                holder.isPresent.setVisibility(View.INVISIBLE);
            }

        }

        /*最后一个Item要加载底部边距*/
        if(position == productsList.size()-1){
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)holder.itemLinearLayout.getLayoutParams();
            params.bottomMargin = UtilClass.dpToPx(context, 10);
            holder.itemLinearLayout.setLayoutParams(params);
        }

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

}
