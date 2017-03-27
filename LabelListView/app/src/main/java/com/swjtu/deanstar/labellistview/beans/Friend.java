package com.swjtu.deanstar.labellistview.beans;

import android.graphics.Bitmap;

/**
 * Created by yhp5210 on 2017/3/27.
 */

public class Friend {

    private int id;
    private String name;
    private Bitmap icon;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
