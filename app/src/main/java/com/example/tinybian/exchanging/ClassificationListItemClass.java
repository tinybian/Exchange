package com.example.tinybian.exchanging;

/**
 * Created by tinybian on 2015/12/1.
 */
public class ClassificationListItemClass {

    private int type;
    private Object item;

    public ClassificationListItemClass(int type, Object item){
        this.type = type;
        this.item = item;
    }

    public int getType(){
        return type;
    }

    public Object getItem(){
        return item;
    }
}
