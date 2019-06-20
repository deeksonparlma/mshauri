package com.epicodus.mshauri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class signup extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.loginsection) TextView mLogin;
@BindView(R.id.signupusername) EditText mUsername;
@BindView(R.id.signupemail) EditText mEmail;
@BindView(R.id.passwordd) EditText mPassword;
@BindView(R.id.confirmpassword) EditText mConfirmPassword;
@BindView(R.id.signupbutton) Button mSignUp;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v ==mLogin){
            Intent intent = new Intent(signup.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            finish();
        }
        if(v==mSignUp){
            String username= mUsername.getText().toString();
            String email = mEmail.getText().toString();
            String password =mPassword.getText().toString();
            String confirmPassword = mConfirmPassword.getText().toString();

            if (username.isEmpty()) {
                mUsername.setError("Username is required");
            }
            else if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                mEmail.setError("Invalid email");
            }
            else if (password.isEmpty()) {
                mPassword.setError("invalid password");
            }
            else if (!confirmPassword.matches(password)) {
                mConfirmPassword.setError("password mismatch");
            }

            else if(!email.isEmpty() &&  confirmPassword.matches(password) && !username.isEmpty()){
                String username2= mUsername.getText().toString();
                String email2 = mEmail.getText().toString();
                String password2 =mPassword.getText().toString();
                String confirmPassword2 = mConfirmPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
                                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            if (snapshot.hasChild(username2)) {
                                                mUsername.setError("Username already exists");
                                            }
                                            else{
                                                FirebaseUser current = mAuth.getCurrentUser();
                                                String uid = current.getUid();
                                                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
                                                myRef.child("username").setValue(username2);
                                                myRef.child("Email").setValue(email2);
                                                myRef.child("password").setValue(confirmPassword2);
                                                Intent intent = new Intent(signup.this, homeActivity.class);
                                                startActivityForResult(intent, 0);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(signup.this, "Sign up failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        }
    }
}
