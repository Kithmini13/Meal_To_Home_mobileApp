package com.scorpion.mealtohome.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scorpion.mealtohome.R;
//order
public class Orderdetails  extends AppCompatActivity {

    TextView tvFO,tvFOTotal,tvOnePrice;
    Button btnAdd1;
    String oderName, oderPrice;
    private static int _counter = 1;
    private String _stringVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        tvFO = findViewById(R.id.textView7);
        tvFOTotal = findViewById(R.id.textView9);
        tvOnePrice = findViewById(R.id.textView8);
        btnAdd1 = findViewById(R.id.button);

        oderName = getIntent().getStringExtra("oderName");
        oderPrice = getIntent().getStringExtra("oderPrice");
        tvFO.setText(oderName);
        tvOnePrice.setText(oderPrice);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _counter++;
                _stringVal = Integer.toString(_counter);

                String p1 = tvOnePrice.getText().toString().trim();
                int cp = Integer.valueOf(p1);
                int val = Integer.valueOf(_stringVal);

                if((val) > 1){
                    Log.e("src", "Total value..."+ val*cp);

                }
                Log.e("src", "Increasing value..."+ _stringVal);
            }
        });

    }
}