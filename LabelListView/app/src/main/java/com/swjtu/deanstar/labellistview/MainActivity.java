package com.swjtu.deanstar.labellistview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swjtu.deanstar.labellistview.view.LabelListView;

import java.util.List;

public class MainActivity extends Activity {

    private LabelListView mLabelListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabelListView = (LabelListView) findViewById(R.id.listView);
    }

    static class DemoAdapter extends RecyclerView.Adapter {

        private static final int COUNT = 2;
        private static final int DATA_TYPE = 1;
        private static final int DIVIDER_TYPE = 2;
        private List<Object> contents;

        public DemoAdapter(List<Object> contents){
            this.contents = contents;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View dataView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.normalitem_layout, parent, false);
            View dividerView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.divideritem_layout, parent, false);
            if (viewType == DATA_TYPE) {

                return new DataViewHolder(dataView);
            } else if (viewType == DIVIDER_TYPE) {
                return new DividerViewHolder(dividerView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Object obj = contents.get(position);
            if(DATA_TYPE == getItemViewType(position)){


            }else if(DIVIDER_TYPE == getItemViewType(position)){

            }
        }

        @Override
        public int getItemCount() {
            return contents.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
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
