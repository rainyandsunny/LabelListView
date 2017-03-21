package com.swjtu.deanstar.labellistview;

import android.app.Activity;
import android.os.Bundle;

import com.swjtu.deanstar.labellistview.view.LabelListView;

public class MainActivity extends Activity {

    private LabelListView mLabelListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabelListView = (LabelListView) findViewById(R.id.listView);
       // FriendAdapter adapter = new FriendAdapter();
       // mLabelListView.setAdapter();
    }
}
