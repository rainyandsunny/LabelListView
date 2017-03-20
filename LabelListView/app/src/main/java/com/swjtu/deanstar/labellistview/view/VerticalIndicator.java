package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swjtu.deanstar.labellistview.util.ViewUtil;

/**
 * 好友列表右侧指示器
 * Created by yhp5210 on 2017/3/17.
 */

public class VerticalIndicator extends LinearLayout {

    private static final String TAG = "VerticalIndicator";
    private static final int WIDTH = 50;
    private static final int HEIGHT = 200;

    private int mWidth;
    private int mHeight;

    private TextView mTvIndicator;
    private Context mContext;
    private String mIndicatorLabel = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
    private boolean mIsnotReady = true;

    public VerticalIndicator(Context context) {
        super(context);
        mContext = context;
    }

    public VerticalIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public VerticalIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = ViewUtil.getSize(widthMeasureSpec,WIDTH);
        mHeight = ViewUtil.getSize(heightMeasureSpec, HEIGHT);
        setMeasuredDimension(mWidth,mHeight);
        if(mIsnotReady){
            mTvIndicator = new TextView(mContext);
            mTvIndicator.setEms(1);
            mTvIndicator.setBackgroundColor(Color.GREEN);
            mTvIndicator.setText(mIndicatorLabel);
            LinearLayout.LayoutParams indicatorParams = new LinearLayout
                    .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            indicatorParams.gravity = Gravity.CENTER_HORIZONTAL;
            addView(mTvIndicator,indicatorParams);
            mIsnotReady = false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action){

            case MotionEvent.ACTION_DOWN:{

            }break;
            case MotionEvent.ACTION_HOVER_MOVE:{

            }break;
            case MotionEvent.ACTION_UP:{

            }break;
        }
        return super.onTouchEvent(event);
    }
}
