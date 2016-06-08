package com.example.tinybian.exchanging;

/**
 * Created by tinybian on 2015/11/30.
 */
public class SettingsListItemClass {

    private String name;
    private int type;

    public SettingsListItemClass(int type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public int getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
