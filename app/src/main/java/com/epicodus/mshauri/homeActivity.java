package com.epicodus.mshauri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.mshauri.adapter.AwarenessPostsAdapter;
import com.epicodus.mshauri.maps.MapsActivity;
import com.epicodus.mshauri.model.AwarenessModel;
import com.epicodus.mshauri.service.mshauriService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class homeActivity extends AppCompatActivity  implements View.OnClickListener{
    @BindView(R.id.foundation1) ImageView mImage;
    @BindView(R.id.forumOption) ImageView mForum;
    @BindView(R.id.foundation2) TextView mText;
    @BindView(R.id.donate) ImageView mDonate;
    @BindView(R.id.reportt) TextView mReport;

    ArrayList<AwarenessModel> Awareness = new ArrayList<>();
    @BindView(R.id.floatingActionButton2)
    FloatingActionButton mChat;
    private AwarenessPostsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mImage.setOnClickListener(this);
        mText.setOnClickListener(this);
        mDonate.setOnClickListener(this);
        mChat.setOnClickListener(this);
        mForum.setOnClickListener(this);
        mReport.setOnClickListener(this);
        getPosts();
    }
//    private void dialog(){
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setPositiveButton(CharSequence text,
//                DialogInterface.OnClickListener listener)
//        alertDialogBuilder.setNegativeButton(CharSequence text,
//                DialogInterface.OnClickListener listener)
//    }
    public void getPosts() {
        final mshauriService service = new mshauriService();
        service.getAwarenessPosts(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Awareness = service.awarenessPosts(response);
                Thread home= new Thread();
                homeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AwarenessPostsAdapter(getApplicationContext(),Awareness);
                        RecyclerView mRecyclerView = findViewById(R.id.postRecyclerView);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(homeActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mImage || v ==mText){
            Intent intent = new Intent(homeActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if(v==mDonate){
            Intent intent = new Intent(homeActivity.this, DonationsActivity.class);
            startActivity(intent);
        }
        else if(v==mReport){
            Intent intent = new Intent(homeActivity.this, ReportActivity.class);
            startActivity(intent);
        }
        else if(v==mChat){
            Intent intent = new Intent(homeActivity.this, MshaurichatActivity.class);
            startActivity(intent);
        }
        else if(v==mForum){
        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity.this);
        builder.setMessage("Do you want to post in the forum ?");
        builder.setTitle("POST/VIEW Forums !");
        builder.setCancelable(false);
        builder
            .setPositiveButton(
                "Yes",
                new DialogInterface
                    .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {

                            Intent intent = new Intent(homeActivity.this, postToForumActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
        builder
            .setNegativeButton(
                "No",
                new DialogInterface
                    .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {

                            // If user click no
                            // then dialog box is canceled.
                            dialog.cancel();
                        }
                    });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
      }
    }
}
