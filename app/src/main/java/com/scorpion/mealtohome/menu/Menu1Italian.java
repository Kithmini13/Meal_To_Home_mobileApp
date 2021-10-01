package com.scorpion.mealtohome.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.order.Orderdetails;

import java.io.ByteArrayOutputStream;

public class Menu1Italian extends AppCompatActivity {

    Button btnSriLanka, btnIndian, btnItalian, btnChinese;
    TextView tvIT1, tvIT2, tvIT3,textView9,textView10,textView11;
    ImageView imageViewIt1,imageViewIt2,imageViewIt3,imageView13,imageView11,imageView12,imageView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1_italian);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSriLanka = findViewById(R.id.btnSriLankaIt);
        btnIndian = findViewById(R.id.btnIndianIt);
        btnItalian = findViewById(R.id.btnItalianIt);
        btnChinese = findViewById(R.id.btnChineseIt);
        imageViewIt1 = findViewById(R.id.imageViewIt1);
        imageViewIt2 = findViewById(R.id.imageViewIt2);
        imageViewIt3 = findViewById(R.id.imageViewIt3);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView10 = findViewById(R.id.imageView10);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        tvIT1 = findViewById(R.id.tvIT1);
        tvIT2= findViewById(R.id.tvIT2);
        tvIT3 = findViewById(R.id.tvIT3);

        btnSriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1India.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1Italian.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1Chines.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        });

        tvIT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu2.class);

                intent.putExtra("title",textView9.getText().toString());
                intent.putExtra("price","1200.00");

                imageViewIt1.buildDrawingCache();
                Bitmap bitmap = imageViewIt1.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvIT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu2.class);

                intent.putExtra("title",textView10.getText().toString());
                intent.putExtra("price","1500.00");

                imageViewIt2.buildDrawingCache();
                Bitmap bitmap = imageViewIt2.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvIT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu2.class);

                intent.putExtra("title",textView11.getText().toString());
                intent.putExtra("price","1300.00");

                imageViewIt3.buildDrawingCache();
                Bitmap bitmap = imageViewIt3.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Italian.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Italian.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Italian.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Italian.this, UpdateProfileActivity.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Menu1Italian.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}