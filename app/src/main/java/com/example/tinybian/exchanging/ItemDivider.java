package com.example.tinybian.exchanging;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tinybian on 2016/6/16.
 *
 * 分割线
 */
public class ItemDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };
    private static int DIVIDER_HEIGHT;

    private Drawable mDivider;
    private int mOrientation;

    public ItemDivider(Context context, int orientation){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

        DIVIDER_HEIGHT = UtilClass.dpToPx(context, 10);

        if(orientation != LinearLayoutManager.HORIZONTAL
                && orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == LinearLayoutManager.HORIZONTAL){
            drawHorizontal(c, parent);
        }
        else{
            drawVertical(c, parent);
        }
    }

    //水平布局 画竖直分割线
    public void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int count = parent.getChildCount();
        for(int i=0;i<count;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();

            final int left = child.getRight() + params.rightMargin;
            final int right = left + DIVIDER_HEIGHT;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //垂直布局 画水平分割线
    public void drawVertical(Canvas c, RecyclerView parent){
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int count =  parent.getChildCount();
        for(int i = 0; i < count ; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();

            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + DIVIDER_HEIGHT;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
        else {
            outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
        }
    }
}
