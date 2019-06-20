package com.epicodus.mshauri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.epicodus.mshauri.adapter.AwarenessPostsAdapter;
import com.epicodus.mshauri.adapter.FoundationsAdapter;
import com.epicodus.mshauri.model.FoundationModel;
import com.epicodus.mshauri.service.mshauriService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReportActivity extends AppCompatActivity {
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
//        mAdapter = new FoundationsAdapter(getApplicationContext(),mContent);
       getFoundations();
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReportActivity.this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setAdapter(mAdapter);
    }
    public void getFoundations() {
        final mshauriService service = new mshauriService();
        service.getFoundation(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mContent = service.foundations(response);
                ReportActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new FoundationsAdapter(getApplicationContext(),mContent);
                        RecyclerView mRecyclerView = findViewById(R.id.fnd);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ReportActivity.this, LinearLayoutManager.HORIZONTAL, false);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReportActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
            }
        });
//        final mshauriService service = new mshauriService();
//        service.getFoundation(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) { e.printStackTrace(); }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                mContent = service.foundations(response);
//                ReportActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mAdapter = new FoundationsAdapter(getApplicationContext(),mContent);
//                        RecyclerView mRecyclerView = findViewById(R.id.fnd);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReportActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                        mRecyclerView.setAdapter(mAdapter);
//
//                    }
//                });
//
//            }
//        });
    }

//    @Override
//    public void onClick(View v) {
//        if(v==call){
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
////                startActivity(intent);
//        }
//    }
}
