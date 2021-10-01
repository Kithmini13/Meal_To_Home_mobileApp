package com.scorpion.mealtohome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery1;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.menu.Menu1India;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.order.takeaway;

public class PwymentMathordActivity extends AppCompatActivity {

    Button btnTW,btnDe;
    TextView tvA;
    String totAmount,amount;
    ImageView imageView13,imageView11,imageView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwyment_mathord);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnTW = findViewById(R.id.btnTW);
        btnDe = findViewById(R.id.btnDe);
        tvA = findViewById(R.id.tvA);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

        amount = getIntent().getStringExtra("totalAmount");
        tvA.setText(amount);

        btnTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PwymentMathordActivity.this, takeaway.class);
                startActivity(intent);
                finish();
            }
        });

        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PwymentMathordActivity.this, Delivery1.class);
                totAmount = tvA.getText().toString();
                intent.putExtra("totalAmount", totAmount);
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PwymentMathordActivity.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PwymentMathordActivity.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PwymentMathordActivity.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(PwymentMathordActivity.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}