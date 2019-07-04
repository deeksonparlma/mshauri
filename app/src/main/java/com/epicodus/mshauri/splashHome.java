package com.epicodus.mshauri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashHome extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);
        mAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run(){
//                        FirebaseUser currentUser = mAuth.getCurrentUser();
//                        if(currentUser == null){
//                            Toast.makeText(splashHome.this, "Welcome to Mshauri_connect",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Intent intent = new Intent(splashHome.this, LoginActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                            startActivityForResult(intent, 0);
//                            finish();
//                        }
                        Intent intent = new Intent(getApplicationContext() , LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivityForResult(intent, 0);
                        finish();
                    }
                },2200);
    }
}
