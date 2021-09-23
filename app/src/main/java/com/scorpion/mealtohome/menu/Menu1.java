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
import com.scorpion.mealtohome.login.Login;
import com.scorpion.mealtohome.login.Login3;
import com.scorpion.mealtohome.order.Orderdetails;

import java.io.ByteArrayOutputStream;

public class Menu1 extends AppCompatActivity {

    Button btnSriLanka, btnIndian, btnItalian, btnChinese;
    TextView tvSL1, tvSL2, tvSL3,textView9,textView10,textView11;
    ImageView imageView7,imageView8,imageView9,imgVProfile,imgCart1;
    String S1,S2,S3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSriLanka = findViewById(R.id.btnSriLanka);
        btnIndian = findViewById(R.id.btnIndian);
        btnItalian = findViewById(R.id.btnItalian);
        btnChinese = findViewById(R.id.btnChinese);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imgCart1 = findViewById(R.id.imgCart1);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        imgVProfile = findViewById(R.id.imgVProfile);

        tvSL1 = findViewById(R.id.tvSL1);
        tvSL2 = findViewById(R.id.tvSL2);
        tvSL3 = findViewById(R.id.tvSL3);

        btnSriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu1.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        }); btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu1India.class);
                startActivity(intent);
                finish();
            }
        }); btnItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu1Italian.class);
                startActivity(intent);
                finish();
            }
        }); btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu1Chines.class);
                startActivity(intent);
                finish();
            }
        });

        tvSL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu2.class);

                S1 = textView9.getText().toString();
                intent.putExtra("title",S1);
                intent.putExtra("price","200.00");

                imageView7.buildDrawingCache();
                Bitmap bitmap = imageView7.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvSL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu2.class);

                S2 = textView10.getText().toString();
                intent.putExtra("title",S2);
                intent.putExtra("price","4200.00");

                imageView8.buildDrawingCache();
                Bitmap bitmap = imageView8.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvSL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, Menu2.class);

                S3 = textView11.getText().toString();
                intent.putExtra("title",S3);
                intent.putExtra("price","440.00");

                imageView9.buildDrawingCache();
                Bitmap bitmap = imageView9.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });

        imgVProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1.this, UpdateProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu1.this, Orderdetails.class);
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
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}