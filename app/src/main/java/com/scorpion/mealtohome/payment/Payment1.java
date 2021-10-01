package com.scorpion.mealtohome.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery2;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;

public class Payment1 extends AppCompatActivity {

    Button btnCard,btnCash;
    TextView tvA3;
    String amount,totAmount;
    ImageView imageView13,imageView14,imageView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment1);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnCash = findViewById(R.id.btnCash);
        btnCard = findViewById(R.id.btnCard);
        tvA3 = findViewById(R.id.tvA3);
        imageView13 = findViewById(R.id.imageButton13);
        imageView14 = findViewById(R.id.imageButton14);
        imageView15 = findViewById(R.id.imageButton15);

        amount = getIntent().getStringExtra("totalAmount");
        tvA3.setText(amount);

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment1.this, Payment2.class);
                totAmount = tvA3.getText().toString();
                intent.putExtra("totalAmount", totAmount);
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment1.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment1.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment1.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Payment1.this, Delivery2.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}