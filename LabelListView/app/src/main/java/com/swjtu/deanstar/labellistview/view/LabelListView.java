package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swjtu.deanstar.labellistview.R;
import com.swjtu.deanstar.labellistview.util.ViewUtil;

/**
 * Created by yhp5210 on 2017/3/16.
 */

public class LabelListView extends RelativeLayout implements LabelTextView.UpadateStatus{

    private static final String TAG = "LabelListView";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int INDICATOR_IWDTH = 60;
    private int mWidth;
    private int mHeight;

    private LabelTextView mTvIndicator;
    private Context mContext;
    private String mIndicatorLabel = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ#"; //TODO
    private boolean mIsnotReady = true;
    private TextView mToastTextView;
    private RecyclerView mContentListView;


    public LabelListView(Context context) {
        super(context);
        mContext = context;
    }
    public LabelListView(Context context, AttributeSet attrs) {
        super(context);
        mContext = context;
    }
    public LabelListView(Context context, AttributeSet attrs, int defStyleAttr) {
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

            mTvIndicator = new LabelTextView(mContext,this);
            mTvIndicator.setmTextSize(ViewUtil.dip2px(mContext,15));
            mTvIndicator.setmLabels(mIndicatorLabel);
            RelativeLayout.LayoutParams indicatorParams = new RelativeLayout
                    .LayoutParams(INDICATOR_IWDTH, RelativeLayout.LayoutParams.MATCH_PARENT);
            indicatorParams.addRule(ALIGN_PARENT_RIGHT);
            addView(mTvIndicator,indicatorParams);

            //添加中间提示框
            mToastTextView = new TextView(mContext);
            mToastTextView.setTextSize(20);
            mToastTextView.setGravity(Gravity.CENTER);
            mToastTextView.setTextColor(Color.WHITE);
            mToastTextView.setText("A");
            mToastTextView.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams toastParams = new RelativeLayout
                    .LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            toastParams.addRule(CENTER_IN_PARENT);
            mToastTextView.setBackgroundDrawable(getResources()
                    .getDrawable(R.drawable.indicatorbackground));
            addView(mToastTextView,toastParams);

            //添加listView
            mContentListView = new RecyclerView(mContext);
            RelativeLayout.LayoutParams listViewParams = new RelativeLayout
                    .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(mContentListView,listViewParams);
            mIsnotReady = false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    public void updateIndicator(int index) {


        if(null != mIndicatorLabel){
            mToastTextView.setText(String.valueOf(mIndicatorLabel.charAt(index)));
        }

    }

    @Override
    public void showToast(boolean status) {
        if(status){
            mToastTextView.setVisibility(View.VISIBLE);
        }else{
            mToastTextView.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d(TAG,"dispatchTouchEvent" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d(TAG,"onInterceptTouchEvent" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    public void setAdapter(RecyclerView.Adapter adapter){

        mContentListView.setAdapter(adapter);
    }

}
