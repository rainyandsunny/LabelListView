package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.swjtu.deanstar.labellistview.R;
import com.swjtu.deanstar.labellistview.util.ViewUtil;

/**
 * 好友列表右侧指示器
 * Created by yhp5210 on 2017/3/20.
 */

public class LabelTextView extends View {

    private static final String TAG = "LabelTextView";
    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;

    private int mWidth;
    private int mHeight;
    private String mLabels;
    private int mTextSize = 30;
    private Paint mPaint;
    private Context mContext;
    private View mParentView;



    public LabelTextView(Context context,View view) {
        super(context);
        initResources();
        mContext = context;
        mParentView = view;
    }

    public LabelTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources();
        mContext = context;
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources();
        mContext = context;
    }

    public String getmLabels() {
        return mLabels;
    }

    public void setmLabels(String mLabels) {
        this.mLabels = mLabels;
    }

    public int getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(getResources().getDrawable(R.drawable.headerbackground));
        mPaint.setTextSize(mTextSize);
        if (null != mLabels) {
            float x, y, yStep;
            int len = mLabels.length();

            x = mWidth * 0.5f;
            y = mHeight * 1.0f / len;
            yStep = mHeight * 1.0f / len;
            for (int i = 0; i < len; i++) {

                if (0 != i) {
                    y += yStep;
                }
                String indicator = Character.toString(mLabels.charAt(i));
                /*Log.d(TAG, "x = " + x + "y = " + y + "mWidth = "
                        + mWidth + "mHeight = " + mHeight + "len = " + len);*/
                canvas.drawText(indicator, x, y, mPaint);
            }
            canvas.save();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = ViewUtil.getSize(widthMeasureSpec, WIDTH);
        mHeight = ViewUtil.getSize(heightMeasureSpec, HEIGHT);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.d(TAG,"dispatchTouchEvent" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    public void initResources() {

        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        float y = event.getY();
        if(null == mLabels){
            return false;
        }
        switch (action) {

            case MotionEvent.ACTION_DOWN: {
                Log.d(TAG, "action_down");
                ((UpadateStatus)(mParentView)).showToast(true);
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                Log.d(TAG, "action_move");
                int position = (int) (y / (mHeight / mLabels.length()));
                if(position < 0){
                    position = 0;
                }else if(position >= mLabels.length()){
                    position = mLabels.length() - 1;
                }
                ((UpadateStatus)(mParentView)).updateIndicator(position);
            }
            break;
            case MotionEvent.ACTION_UP: {
                Log.d(TAG, "action_up");
                ((UpadateStatus)(mParentView)).showToast(false);
            }
            break;
        }

        return true;
    }



    public interface UpadateStatus{

        public void updateIndicator(int index);
        public void showToast(boolean status);
    }

}
