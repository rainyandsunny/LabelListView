package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
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

public class LabelListView extends RelativeLayout implements LabelTextView.UpadateStatus {

    private static final String TAG = "LabelListView";
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int INDICATOR_IWDTH = 60;
    private int mWidth;
    private int mHeight;

    private LabelTextView mTvIndicator;
    private Context mContext;
    private String mIndicatorLabel = "☆ABCDEFGHIJKLMNOPQRSTUVWXYZ#"; //TODO
    private TextView mToastTextView;
    private RecyclerView mContentListView;
    private RecyclerView.LayoutManager mLayoutManager;

    public LabelListView(Context context) {
        super(context);
        mContext = context;
        initViews();
    }

    public LabelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
    }

    public LabelListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = ViewUtil.getSize(widthMeasureSpec, WIDTH);
        mHeight = ViewUtil.getSize(heightMeasureSpec, HEIGHT);
        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    public void updateIndicator(int index) {


        String label = String.valueOf(mIndicatorLabel.charAt(index));
        if (null != mIndicatorLabel) {
            mToastTextView.setText(label);
        }
        mLayoutManager.scrollToPosition(index*2);

    }

    @Override
    public void showToast(boolean status) {
        if (status) {
            mToastTextView.setVisibility(View.VISIBLE);
        } else {
            mToastTextView.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d(TAG, "dispatchTouchEvent" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.d(TAG, "onInterceptTouchEvent" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    public void initViews() {


        //添加listView
        mContentListView = new RecyclerView(mContext);
        RelativeLayout.LayoutParams listViewParams = new RelativeLayout
                .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLayoutManager = new LinearLayoutManager(mContext);
        mContentListView.setLayoutManager(mLayoutManager);
        addView(mContentListView, listViewParams);


        mTvIndicator = new LabelTextView(mContext, this);
        mTvIndicator.setmTextSize(ViewUtil.dip2px(mContext, 15));
        mTvIndicator.setmLabels(mIndicatorLabel);
        mTvIndicator.setBackgroundResource(R.drawable.headerbackground);
        RelativeLayout.LayoutParams indicatorParams = new RelativeLayout
                .LayoutParams(INDICATOR_IWDTH, RelativeLayout.LayoutParams.MATCH_PARENT);
        indicatorParams.addRule(ALIGN_PARENT_RIGHT);
        addView(mTvIndicator, indicatorParams);


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
        addView(mToastTextView, toastParams);



    }

    public void setAdapter(RecyclerView.Adapter adapter) {

        mContentListView.setAdapter(adapter);


    }

    public static class DataItem {

        public static final int DATA_TYPE = 1;
        public static final int DIVIDER_TYPE = 2;
        private int type;
        private Object content;

        public DataItem(Object content, int type) {
            this.content = content;
            this.type = type;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
