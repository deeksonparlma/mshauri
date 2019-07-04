package com.epicodus.mshauri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.mshauri.model.ChatMessage;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MshaurichatActivity extends AppCompatActivity implements View.OnClickListener{
    private Timer autoUpdate;
    @BindView(R.id.chatshimmer)
    ShimmerFrameLayout mShimmer;
    private FirebaseListAdapter<ChatMessage> adapter;
    @BindView(R.id.send2)
    ImageView sendMessage;
    @BindView(R.id.input) EditText mMessage;
    ArrayList<String> messages = new ArrayList<>();
    @BindView(R.id.listMessages) ListView allMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mshaurichat);
        ButterKnife.bind(this);
        sendMessage.setOnClickListener(this);
        displayChatMessages();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        show();
    }


    private void show() {
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current.getUid();
        FirebaseDatabase.getInstance().getReference("forum").child("people")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String user = snapshot.getValue(String.class);
                            System.out.println(user);
                            messages.add(user);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MshaurichatActivity.this, R.layout.list,R.id.sss, messages);
                        allMessages.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
    private void details(){
//        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
////        current.getUid();
//        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
//        DatabaseReference username = myRef.child(current.getUid()).child("username");
//        username.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String username = dataSnapshot.getValue(String.class);
//                mUsername =username;
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
    private void displayChatMessages() {
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current.getUid();
        FirebaseDatabase.getInstance().getReference("forum").child("people").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        ArrayList<String> fineText = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            ChatMessage user = snapshot.getValue(ChatMessage.class);
                            String user = snapshot.getValue(String.class);
                            messages.removeAll(messages);
                            messages.add(user);
//                            fineText.add(user);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MshaurichatActivity.this, android.R.layout.simple_list_item_1, messages);
                        allMessages.setAdapter(adapter);
                        mShimmer.setVisibility(View.INVISIBLE);
                        mShimmer.stopShimmer();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        EditText input = (EditText)findViewById(R.id.input);
        if(v == sendMessage){
//            details();
            String u = "";
            FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
//        current.getUid();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
            DatabaseReference username = myRef.child(current.getUid()).child("username");
            String message = mMessage.getText().toString().trim();
            if (message.isEmpty()){
                mMessage.setError("Cannot be empty");
                return;
            }
            username.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.getValue(String.class);
                    String Userusername = "@"+username;

//                    String packet = "@"+username+": "+message;
                   String packet = String.format("%s \n %s", Userusername,message);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid2 = user.getUid();
                    FirebaseDatabase.getInstance()
                            .getReference("forum").child("people")
                            .push()
                            .setValue(packet);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            input.setText("");
            messages.removeAll(messages);
            show();
        }
        }

    public void displaychats(){
        String[] arrayy = messages.toArray( new String[messages.size()]);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayy);
        allMessages.setAdapter(adapter);

    }
    @Override
    public void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        messages.clear();
                        show();
                    }
                });
            }
        }, 0, 4000); // updates each 40 secs
    }

}
