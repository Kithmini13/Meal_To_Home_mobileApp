package com.scorpion.mealtohome.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scorpion.mealtohome.R;

import java.io.BufferedInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payment2 extends AppCompatActivity {

    Button btnPaymentSubmit;
    EditText etAmount,etCardOwnerName,etDate;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        etAmount = findViewById(R.id.etAmount);
        etCardOwnerName = findViewById(R.id.etCardOwnerName);
        etDate = findViewById(R.id.etDate);
        btnPaymentSubmit = findViewById(R.id.btnPaymentSubmit);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        date = dateFormat.format(calendar.getTime());
        etDate.setText(date);

        amount = getIntent().getStringExtra("totalAmount");
        etAmount.setText(amount);



    }
}