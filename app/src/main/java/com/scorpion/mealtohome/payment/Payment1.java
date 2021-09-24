package com.scorpion.mealtohome.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.delivery.Delivery2;

public class Payment1 extends AppCompatActivity {

    Button btnCard,btnCash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment1);

        btnCash = findViewById(R.id.btnCash);
        btnCard = findViewById(R.id.btnCard);

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment1.this, Payment2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}