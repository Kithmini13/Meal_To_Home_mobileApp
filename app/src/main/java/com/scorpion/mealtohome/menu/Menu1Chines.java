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

import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.order.Orderdetails;

import java.io.ByteArrayOutputStream;

public class Menu1Chines extends AppCompatActivity {

    Button btnSriLanka, btnIndian, btnItalian, btnChinese;
    TextView tvC1, tvC2, tvC3,textView9,textView10,textView11;
    ImageView imageViewC,imageViewC2,imageViewC3,imageView13,imageView11,imageView12,imageView10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1_chines);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSriLanka = findViewById(R.id.btnSriLankaC);
        btnIndian = findViewById(R.id.btnIndianC);
        btnItalian = findViewById(R.id.btnItalianC);
        btnChinese = findViewById(R.id.btnChineseC);
        imageViewC = findViewById(R.id.imageViewC);
        imageViewC2 = findViewById(R.id.imageViewC2);
        imageViewC3 = findViewById(R.id.imageViewC3);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView10 = findViewById(R.id.imageView10);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);

        tvC1 = findViewById(R.id.tvC1);
        tvC2= findViewById(R.id.tvC2);
        tvC3 = findViewById(R.id.tvC3);

        btnSriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu1.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu1India.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu1Italian.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        }); btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu1Chines.class);
                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
                startActivity(intent);
                finish();
            }
        });

        tvC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu2.class);

                intent.putExtra("title",textView9.getText().toString());
                intent.putExtra("price","700.00");

                imageViewC.buildDrawingCache();
                Bitmap bitmap = imageViewC.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu2.class);

                intent.putExtra("title",textView10.getText().toString());
                intent.putExtra("price","1000.00");

                imageViewC2.buildDrawingCache();
                Bitmap bitmap = imageViewC2.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Chines.this, Menu2.class);

                intent.putExtra("title",textView11.getText().toString());
                intent.putExtra("price","1300.00");

                imageViewC3.buildDrawingCache();
                Bitmap bitmap = imageViewC3.getDrawingCache();
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
                Intent intent=new Intent(Menu1Chines.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Chines.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Chines.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1Chines.this, UpdateProfileActivity.class);
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
                startActivity(new Intent(Menu1Chines.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}