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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationsActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.Donationbutton) Button Donate;
    @BindView(R.id.number) EditText mNumber;
    @BindView(R.id.amount) EditText mAmount;
    private Daraja mdaraja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);
        ButterKnife.bind(this);
        Donate.setOnClickListener(this);
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


            Daraja daraja = Daraja.with("nrNgs4hdS0kkTOqqp6h5et24nlfqfJO8", "Syal7zSIUHO67ffN", new DarajaListener<AccessToken>() {
                @Override
                public void onResult(AccessToken accessToken) {
                    Log.i(DonationsActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
                }

                @Override
                public void onError(String error) {
                    Log.e(DonationsActivity.this.getClass().getSimpleName(), error);

                }
            });
            LNMExpress lnmExpress = new LNMExpress(
                    "42365",
                    "tArKHN2gfhTYSrq76gR8siM38F4l",  //https://developer.safaricom.co.ke/test_credentials
                    TransactionType.CustomerPayBillOnline,
                    Amount,
                    "254740392957",
                    "423655",
                    "0740392957",
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
