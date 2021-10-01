package com.scorpion.mealtohome.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.order.Orderdetails;

public class Menu2 extends AppCompatActivity {

    ImageView imgView;
    TextView title1,tvPrice;
    String title,price;
    Button btnOder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnOder = findViewById(R.id.btnOder);
        imgView = findViewById(R.id.imgView);
        title1 = findViewById(R.id.title1);
        tvPrice = findViewById(R.id.tvPrice);

        title = getIntent().getStringExtra("title");
        price = getIntent().getStringExtra("price");
        title1.setText(title);
        tvPrice.setText(price);

        if(getIntent().hasExtra("byteArray")) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
            imgView.setImageBitmap(b);
        }

        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu2.this, Orderdetails.class);
                String oderName = title1.getText().toString();
                String oderPrice = tvPrice.getText().toString();
                intent.putExtra("oderName",oderName);
                intent.putExtra("oderPrice",oderPrice);
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
                startActivity(new Intent(Menu2.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}