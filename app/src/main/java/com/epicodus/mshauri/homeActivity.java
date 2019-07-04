package com.epicodus.mshauri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.mshauri.adapter.AwarenessPostsAdapter;
import com.epicodus.mshauri.maps.MapsActivity;
import com.epicodus.mshauri.model.AwarenessModel;
import com.epicodus.mshauri.service.mshauriService;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

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
    @BindView(R.id.report1) ImageView mReport1;
    @BindView(R.id.foundation2) TextView mText;
    @BindView(R.id.donate) ImageView mDonate;
    @BindView(R.id.forumtext) TextView mForumText;
    @BindView(R.id.donation2) TextView mDonate2;
    @BindView(R.id.reportt) TextView mReport;
    @BindView(R.id.logout) Button mLogout;
    @BindView(R.id.parentShimmerLayout) ShimmerFrameLayout mShimmer;
    ArrayList<AwarenessModel> Awareness = new ArrayList<>();
    @BindView(R.id.floatingActionButton2)
    FloatingActionButton mChat;
    @BindView(R.id.postRecyclerView) RecyclerView mRec;
    private AwarenessPostsAdapter mAdapter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mImage.setOnClickListener(this);
        mText.setOnClickListener(this);
        mDonate.setOnClickListener(this);
        mChat.setOnClickListener(this);
        mForum.setOnClickListener(this);
        mReport.setOnClickListener(this);
        mReport1.setOnClickListener(this);
        mForumText.setOnClickListener(this);
        mDonate2.setOnClickListener(this);
        mLogout.setOnClickListener(this);
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
                        mShimmer.stopShimmer();
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
        else if(v == mLogout){
            mAuth.signOut();
            Intent intent = new Intent(homeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v==mDonate || v==mDonate2){
            Intent intent = new Intent(homeActivity.this, DonationsActivity.class);
            startActivity(intent);
        }
        else if(v==mReport || v==mReport1){
            Intent intent = new Intent(homeActivity.this, ReportActivity.class);
            startActivity(intent);
        }
        else if(v==mChat ){
            Intent intent = new Intent(homeActivity.this, MshaurichatActivity.class);
            startActivity(intent);
        }
        else if(v==mForum|| v==mForumText){
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
