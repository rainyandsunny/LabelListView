package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.swjtu.deanstar.labellistview.util.ViewUtil;

/**
 * Created by yhp5210 on 2017/3/16.
 */

public class LabelListView extends RelativeLayout {

    private static final String TAG = "VerticalIndicator";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int INDICATOR_IWDTH = 60;
    private int mWidth;
    private int mHeight;

    private LabelTextView mTvIndicator;
    private Context mContext;
    private String mIndicatorLabel = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ#"; //TODO
    private boolean mIsnotReady = true;


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
            mTvIndicator = new LabelTextView(mContext);
            mTvIndicator.setmTextSize(ViewUtil.dip2px(mContext,15));
            mTvIndicator.setmLabels(mIndicatorLabel);
            RelativeLayout.LayoutParams indicatorParams = new RelativeLayout
                    .LayoutParams(INDICATOR_IWDTH, RelativeLayout.LayoutParams.MATCH_PARENT);
            indicatorParams.addRule(ALIGN_PARENT_RIGHT);
            addView(mTvIndicator,indicatorParams);
            mIsnotReady = false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
