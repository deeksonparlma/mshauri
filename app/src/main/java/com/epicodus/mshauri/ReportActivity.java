package com.epicodus.mshauri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.epicodus.mshauri.adapter.AwarenessPostsAdapter;
import com.epicodus.mshauri.adapter.FoundationsAdapter;
import com.epicodus.mshauri.model.FoundationModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends AppCompatActivity {
    private  String[] location ={"Wangu Kanja"};
    private  String[] number={"0862783842"};
    private FoundationsAdapter mAdapter;
    ArrayList<FoundationModel> mContent = new ArrayList<>();
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.fnd) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);
        FoundationModel model = new FoundationModel("4","77","Wangu Kanja","dddd");
        FoundationModel model2 = new FoundationModel("40","279","Nairobi Women","dddd");
        FoundationModel model3 = new FoundationModel("360","979","Mshauri COnnect","dddd");
        mContent.add(model);
        mContent.add(model2);
        mContent.add(model3);
        mAdapter = new FoundationsAdapter(getApplicationContext(),mContent);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReportActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }
}
