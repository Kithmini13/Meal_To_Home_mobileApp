package com.scorpion.mealtohome.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.scorpion.mealtohome.R;

public class Login extends AppCompatActivity {

    Context mContext;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Login.this,Login3.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}