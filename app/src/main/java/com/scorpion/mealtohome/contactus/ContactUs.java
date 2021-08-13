package com.scorpion.mealtohome.contactus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.scorpion.mealtohome.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }


   public void submitForm(View v){
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Thank You!\nWe'll get back to you soon!");

        pDialog.setCancelable(false);
        pDialog.show();
    }
}