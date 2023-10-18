package com.javatechig.apps.nav;

import android.graphics.drawable.Drawable;

/**
 * Created by Nilanchala
 */
public class DrawerItem {
    private String mText;
    private Drawable mDrawable;

    public DrawerItem(String text, Drawable drawable) {
        mText = text;
        mDrawable = drawable;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }
}
