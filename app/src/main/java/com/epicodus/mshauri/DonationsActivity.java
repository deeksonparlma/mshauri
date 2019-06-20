package com.epicodus.mshauri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidstudy.daraja.Daraja;
import com.androidstudy.daraja.DarajaListener;
import com.androidstudy.daraja.model.AccessToken;
import com.androidstudy.daraja.model.LNMExpress;
import com.androidstudy.daraja.model.LNMResult;
import com.androidstudy.daraja.util.TransactionType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.Donationbutton) Button Donate;
    @BindView(R.id.number) EditText mNumber;
    @BindView(R.id.amount) EditText mAmount;
    private Daraja daraja;
    private String mSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
        ButterKnife.bind(this);
        mNumber.setEnabled(false);

        show();
        Donate.setOnClickListener(this);

        daraja = Daraja.with("nrNgs4hdS0kkTOqqp6h5et24nlfqfJO8", "Syal7zSIUHO67ffN", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                Log.i(DonationsActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
            }

            @Override
            public void onError(String error) {
                Log.e(DonationsActivity.this.getClass().getSimpleName(), error);

            }
        });
    }
    private void show() {
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current.getUid();
        FirebaseDatabase.getInstance().getReference("users").child(uid).child("phoneNumber")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String number2 = dataSnapshot.getValue(String.class);
                        mNumber.setText(number2);
//                        Log.d("number",number2);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==Donate){
            String phonenumber = mNumber.getText().toString().trim();
            String Amount = mAmount.getText().toString().trim();

            if (TextUtils.isEmpty(phonenumber)) {
                mNumber.setError("Please Provide a Phone Number");
                return;
            }
            if(Amount.isEmpty()){
                mAmount.setError("Amount can't be empty");
                return;
            }
            if(Integer.valueOf(Amount)<50){
                mAmount.setError("Minimum amount is 50/=");
                return;
            }


            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",  //https://developer.safaricom.co.ke/test_credentials
                    TransactionType.CustomerPayBillOnline,
                    Amount,
                    "254740392957",
                    "174379",
                    phonenumber,
                    "http://mycallbackurl.com/checkout.php",
                    "001ABC",
                    "Donate to Mshauri"
            );
            daraja.requestMPESAExpress(lnmExpress,
                    new DarajaListener<LNMResult>() {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Log.i(DonationsActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                        }

                        @Override
                        public void onError(String error) {
                            Log.i(DonationsActivity.this.getClass().getSimpleName(), error);
                        }
                    }
            );
        }
    }
}
