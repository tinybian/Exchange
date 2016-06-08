package com.example.tinybian.exchanging;

import android.graphics.drawable.Drawable;

/**
 * Created by tinybian on 2015/12/22.
 */
public class FavProductsItemClass {
    private int productImage;//此处使用本地drawable测试 之后要改成uri
    private int personImage;
    private String productName;
    private String personName;
    private Boolean isPresent;

    public FavProductsItemClass(int productImage, int personImage, String productName,
                                String personName, Boolean isPresent){
        this.productImage = productImage;
        this.personImage = personImage;
        this.productName = productName;
        this.personName = personName;
        this.isPresent = isPresent;
    }

    public int getProductImage(){
        return productImage;
    }
    public int getPersonImage(){
        return personImage;
    }
    public String getProductName(){
        return productName;
    }
    public String getPersonName(){
        return personName;
    }
    public Boolean getIsPresent(){
        return isPresent;
    }
}
