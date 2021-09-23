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

public class Menu1India extends AppCompatActivity {

    Button btnSriLanka, btnIndian, btnItalian, btnChinese;
    TextView tvIN1, tvIN2, tvIN3;
    ImageView imageViewI1,imageViewI2,imageViewI3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1_india);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSriLanka = findViewById(R.id.btnSriLankaI);
        btnIndian = findViewById(R.id.btnIndianI);
        btnItalian = findViewById(R.id.btnItalianI);
        btnChinese = findViewById(R.id.btnChineseI);
        imageViewI1 = findViewById(R.id.imageViewI1);
        imageViewI2 = findViewById(R.id.imageViewI2);
        imageViewI3 = findViewById(R.id.imageViewI3);

        tvIN1 = findViewById(R.id.tvIN1);
        tvIN2 = findViewById(R.id.tvIN2);
        tvIN3 = findViewById(R.id.tvIN3);

        btnSriLanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        }); btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu1India.class);
                startActivity(intent);
                finish();
            }
        }); btnItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu1Italian.class);
                startActivity(intent);
                finish();
            }
        }); btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu1Chines.class);
                startActivity(intent);
                finish();
            }
        });

        tvIN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu2.class);
                imageViewI1.buildDrawingCache();
                Bitmap bitmap = imageViewI1.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvIN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu2.class);
                imageViewI2.buildDrawingCache();
                Bitmap bitmap = imageViewI2.getDrawingCache();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                intent.putExtra("byteArray", bs.toByteArray());
                startActivity(intent);
                finish();
            }
        });tvIN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu1India.this, Menu2.class);
                imageViewI3.buildDrawingCache();
                Bitmap bitmap = imageViewI3.getDrawingCache();
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