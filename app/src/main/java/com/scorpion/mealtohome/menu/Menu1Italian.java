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

import java.io.ByteArrayOutputStream;

public class Menu1Italian extends AppCompatActivity {

    Button btnSriLanka, btnIndian, btnItalian, btnChinese;
    TextView tvIT1, tvIT2, tvIT3;
    ImageView imageViewIt1,imageViewIt2,imageViewIt3;

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

        tvIT1 = findViewById(R.id.tvIT1);
        tvIT2= findViewById(R.id.tvIT2);
        tvIT3 = findViewById(R.id.tvIT3);

        btnSriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        }); btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1India.class);
                startActivity(intent);
                finish();
            }
        }); btnItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1Italian.class);
                startActivity(intent);
                finish();
            }
        }); btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu1Chines.class);
                startActivity(intent);
                finish();
            }
        });

        tvIT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1Italian.this, Menu2.class);
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
                imageViewIt3.buildDrawingCache();
                Bitmap bitmap = imageViewIt3.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
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