package com.scorpion.mealtohome.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.Customer;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.RagisterActivity;
import com.scorpion.mealtohome.menu.Menu1;

public class Login3 extends AppCompatActivity {
    Activity mActivity;
    Context mContext;
    private ProgressDialog loadingBar;
    private String parentDbName = "Customer";
    EditText etPhone, etPassword;
    Button btnLogin;
    TextView tvSignUpNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        mContext = this;
        loadingBar = new ProgressDialog(this);

        etPhone = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUpNow = findViewById(R.id.tvSignUpNow);

        tvSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RagisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPassenger();
            }
        });
    }

    private void LoginPassenger() {
        String phone = etPhone.getText().toString();
        String pwd = etPassword.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please Enter your Phone No..",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"Please Enter your Password..",Toast.LENGTH_SHORT).show();
        }
        else {
            AllowAccessToRegistration(phone,pwd);

            loadingBar.setTitle("Login Now!");
            loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
        }
    }

    private void AllowAccessToRegistration(String phone, String pwd) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentDbName).child(phone).exists()){
                    Customer passengerData = dataSnapshot.child(parentDbName).child(phone).getValue(Customer.class);

                    if (passengerData.getPhone().equals(phone)){
                        if (passengerData.getPassword().equals(pwd)){
                            Toast.makeText(Login3.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent b = new Intent(Login3.this, Menu1.class);
//                            b.putExtra("phoneNo",phone);
                            startActivity(b);

                        }
                    }
                }
                else {
                    Toast.makeText(Login3.this,"Register with this " +phone+ " Number do not exists!",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Login3.this,"You Need to Register!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}