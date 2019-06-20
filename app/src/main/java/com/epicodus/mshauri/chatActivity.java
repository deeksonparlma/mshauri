package com.epicodus.mshauri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.mshauri.model.chat;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class chatActivity extends AppCompatActivity {
    ArrayList<String> chatsArray = new ArrayList<>();
    @BindView(R.id.chatusernamee) TextView mUsername;
    @BindView(R.id.listview) ListView mList;
    private FirebaseAuth mAuth;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        loadchat();
        displaychats();
    }
//    Firebase ref = new Firebase("https://<yourapp>.firebaseio.com");
    public void displaychats(){
        String[] arrayy = chatsArray.toArray( new String[chatsArray.size()]);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayy);
        mList.setAdapter(adapter);
    }
    public void loadchat(){
        FirebaseUser current = mAuth.getCurrentUser();
        String uid = current.getUid();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users").child(uid).child("chat");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue(String.class);
                chatsArray.add(email);
                mListView = (ListView) findViewById(R.id.listview);

                for(int i=0; i < chatsArray.size(); i++){

                    mUsername.setText(mUsername.getText() + " " + chatsArray.get(i) + " , ");
                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

//     chatssection.addListenerForSingleValueEvent(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            String email = dataSnapshot.getValue(String.class);
////            mUsername.setText(email);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    });
//    ListAdapter adapter = new FirebaseListAdapter<chat>(this,
//        chat.class, android.R.layout.two_line_list_item, mRef)
//    {
//        @Override
//        protected void populateView(View v, chat model, int position) {
//
//        }
//
//        protected void populateView(View view, chat chatMessage)
//        {
//            ((TextView)view.findViewById(android.R.id.text1)).setText(chatMessage.getSender());
//            ((TextView)view.findViewById(android.R.id.text2)).setText(chatMessage.getMessage());
//        }
//    };
//     listView.setListAdapter(adapter);
}
