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

        }

        @Override
        public int getItemCount() {
            return 0;
        }


        static class DataViewHolder extends RecyclerView.ViewHolder {

            private ImageView ivUserIcon;
            private TextView tvUserName;

            public DataViewHolder(View itemView) {
                super(itemView);

            }
        }

        static class DividerViewHolder extends RecyclerView.ViewHolder {

            public DividerViewHolder(View itemView) {
                super(itemView);
            }
        }

    }
}
