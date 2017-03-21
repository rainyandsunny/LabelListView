package com.swjtu.deanstar.labellistview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swjtu.deanstar.labellistview.R;
import com.swjtu.deanstar.labellistview.util.ViewUtil;

import java.util.List;

/**
 * Created by yhp5210 on 2017/3/16.
 */

public class LabelListView extends RelativeLayout implements LabelTextView.UpadateStatus{

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
    private TextView mToastTextView;
    private ListView mContentListView;


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
            mToastTextView = new TextView(mContext);
            mToastTextView.setTextSize(20);
            mToastTextView.setGravity(Gravity.CENTER);
            mToastTextView.setTextColor(Color.WHITE);
            mToastTextView.setText("A");
            RelativeLayout.LayoutParams toastParams = new RelativeLayout
                    .LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            toastParams.addRule(CENTER_IN_PARENT);
            mToastTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.indicatorbackground));
            addView(mToastTextView,toastParams);

            mContentListView = new ListView(mContext);
            RelativeLayout.LayoutParams listViewParams = new RelativeLayout
                    .LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
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



    class FriendAdaper extends BaseAdapter {

        private static final int COUNT = 2;
        private static final int DATA_TYPE = 1;
        private static final int DIVIDER_TYPE = 2;
        private List<ListDataItem> friendListItems;
        private DataViewHolder dataViewHolder;
        private DividerViewHolder dividerViewHolder;
        public FriendAdaper(List<ListDataItem> friendListItems) {
            this.friendListItems = friendListItems;
        }

        @Override
        public int getCount() {
            return friendListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return friendListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            int type = getItemViewType(position);

            if (null == convertView) {

                if (type == DATA_TYPE) {

                    dataViewHolder = new DataViewHolder();
                    convertView = LayoutInflater.from(mContext)
                            .inflate(R.layout.normalitem_layout, null);
                    dataViewHolder.img = (ImageView) convertView.findViewById(R.id.ivicon);
                    dataViewHolder.title = (TextView) convertView.findViewById(R.id.tvname);
                    convertView.setTag(dataViewHolder);
                } else if (type == DIVIDER_TYPE) {


                    dividerViewHolder =  new DividerViewHolder();
                    convertView = LayoutInflater.from(mContext)
                            .inflate(R.layout.divideritem_layout, null);
                    dividerViewHolder.label = (TextView) convertView.findViewById(R.id.label);
                    convertView.setTag(dividerViewHolder);
                }


            }else{

                if(type == DATA_TYPE){

                    dataViewHolder = (DataViewHolder) convertView.getTag();
                }else if(type == DIVIDER_TYPE){
                    dividerViewHolder = (DividerViewHolder) convertView.getTag();

                }

            }
            ListDataItem item = friendListItems.get(position);
            if (type == DATA_TYPE){
                /*if (item.object instanceof Friend) {*/
                /*    Friend friend = (Friend) (item.object);*/
                /*    dataViewHolder.img.setImageBitmap(friend.getmBitmap());*/
                /*    dataViewHolder.title.setText(friend.getName());*/
                /*}*/

            }else if(type == DIVIDER_TYPE){

                /*if (item.object instanceof Divider) {*/
/*
*/

                /*    Divider divider = (Divider) (item.object);*/
                /*    dividerViewHolder.label.setText(divider.getLabel());*/
                /*}*/
            }




            return convertView;
        }

        @Override
        public int getItemViewType(int position) {

            return friendListItems.get(position).type;
        }

        @Override
        public int getViewTypeCount() {
            return COUNT;
        }
    }

    static class ListDataItem {

        private Object object;
        private int type;

        public ListDataItem(Object object, int type) {
            this.object = object;
            this.type = type;
        }

    }

    static class Header {

        private String label;
        private int index;

        private Header(String label, int index) {
            this.label = label;
            this.index = index;
        }

    }

    public void setAdapter(FriendAdaper friendAdaper){

        mContentListView.setAdapter(friendAdaper);
    }


    public static final class DataViewHolder {

        public ImageView img;
        public TextView title;
    }

    public static final class DividerViewHolder{

        public TextView label;
    }
}
