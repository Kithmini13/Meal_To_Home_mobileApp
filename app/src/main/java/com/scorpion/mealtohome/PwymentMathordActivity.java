package com.scorpion.mealtohome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.menu.Menu1India;
import com.scorpion.mealtohome.order.takeaway;

public class PwymentMathordActivity extends AppCompatActivity {

    Button btnTW,btnDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwyment_mathord);

        btnTW = findViewById(R.id.btnTW);
        btnDe = findViewById(R.id.btnDe);

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
                Intent intent=new Intent(PwymentMathordActivity.this, takeaway.class);
                startActivity(intent);
                finish();
            }
        });
    }
}