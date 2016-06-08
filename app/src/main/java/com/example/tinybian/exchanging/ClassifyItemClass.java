package com.example.tinybian.exchanging;

/**
 * Created by tinybian on 2016/6/7.
 */
public class ClassifyItemClass {
    private int image;
    private String classifyName;
    private String classifyInfo;

    public ClassifyItemClass(int image, String classifyName, String classifyInfo){
        this.image = image;
        this.classifyInfo = classifyInfo;
        this.classifyName = classifyName;
    }

    public int getImage(){ return image; }
    public String getClassifyName(){ return classifyName; }
    public String getClassifyInfo(){ return classifyInfo; }
}
