package com.swjtu.deanstar.labellistview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swjtu.deanstar.labellistview.beans.Divider;
import com.swjtu.deanstar.labellistview.beans.Friend;
import com.swjtu.deanstar.labellistview.view.LabelListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG ="MainActivity";
    private LabelListView mLabelListView;
    private List<LabelListView.DataItem> mContents;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 控件查找
        mLabelListView = (LabelListView) findViewById(R.id.listView);

        //2. 模拟获取内容
        getData();

        //3. 设置Adapter
        adapter = new DemoAdapter(mContents);
        Log.d(TAG,"adapter=null" + (adapter == null));
        Log.d(TAG,"mContents.size:" + mContents.size());
        mLabelListView.setAdapter(adapter);
    }



    public void getData(){

        mContents = new ArrayList<>();
        for (int i = 0; i < 27; i++) {

            LabelListView.DataItem item = null;
            if (0 == i) {
                Friend firend1 = new Friend();
                Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.a_b, null);
                firend1.setIcon(icon1);
                firend1.setName("新的朋友");
                item =  new LabelListView.DataItem(firend1, 1);
                mContents.add(item);
                Friend firend2 = new Friend();
                Bitmap icon2 = BitmapFactory.decodeResource(getResources(), R.drawable.a_j, null);
                firend2.setIcon(icon2);
                firend2.setName("群聊");
                item = new LabelListView.DataItem(firend2, 1);
                mContents.add(item);
            } else {
                Divider divider = new Divider();
                divider.setLabel("" + (char) (65 + (i - 1)));
                item = new LabelListView.DataItem(divider, 2);
                mContents.add(item);
                Friend firend2 = new Friend();
                Bitmap icon2 = BitmapFactory.decodeResource(getResources(), R.drawable.a_j, null);
                firend2.setIcon(icon2);
                firend2.setName((char) (65 + (i - 1)) + "用户");
                item = new LabelListView.DataItem(firend2, 1);
                mContents.add(item);
            }

        }

    }


    static class DemoAdapter extends RecyclerView.Adapter {

        private List<LabelListView.DataItem> contents;

        public DemoAdapter(List<LabelListView.DataItem> contents){
            this.contents = contents;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View dataView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.normalitem_layout, parent, false);
            View dividerView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.divideritem_layout, parent, false);
            if (viewType == LabelListView.DataItem.DATA_TYPE) {

                return new DataViewHolder(dataView);
            } else{
                return new DividerViewHolder(dividerView);
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Object obj = contents.get(position).getContent();
            if(LabelListView.DataItem.DATA_TYPE == getItemViewType(position)){
                Friend friend = (Friend)obj;
                DataViewHolder dataViewHolder = (DataViewHolder)holder;
                dataViewHolder.tvUserName.setText(friend.getName());
                dataViewHolder.ivUserIcon.setImageBitmap(friend.getIcon());

            }else if(LabelListView.DataItem.DIVIDER_TYPE == getItemViewType(position)){
                Divider divider = (Divider)obj;
                DividerViewHolder dividerViewHolder = (DividerViewHolder)holder;
                dividerViewHolder.tvLabel.setText(divider.getLabel());
            }
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }

        @Override
        public int getItemViewType(int position) {
            return contents.get(position).getType();
        }

        static class DataViewHolder extends RecyclerView.ViewHolder {

            private ImageView ivUserIcon;
            private TextView tvUserName;

            public DataViewHolder(View itemView) {
                super(itemView);
                ivUserIcon = (ImageView) itemView.findViewById(R.id.ivicon);
                tvUserName = (TextView) itemView.findViewById(R.id.tvname);

            }
        }

        static class DividerViewHolder extends RecyclerView.ViewHolder {

            private TextView tvLabel;

            public DividerViewHolder(View itemView) {
                super(itemView);
                tvLabel = (TextView) itemView.findViewById(R.id.label);
            }
        }

    }
}
