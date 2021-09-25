package com.scorpion.mealtohome.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;

//order
public class Orderdetails extends AppCompatActivity {

    TextView tvFO, tvFOTotal, tvOnePrice, tvFullTot;
    Button btnAdd1,btnDic1,btnPayOder;
    String oderName, oderPrice,totAmount;
    private static int _counter = 1;
    private String _stringVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        tvFO = findViewById(R.id.textView7);
        tvFOTotal = findViewById(R.id.textView9);
        tvOnePrice = findViewById(R.id.textView8);
        tvFullTot = findViewById(R.id.tvFullTot);
        btnAdd1 = findViewById(R.id.button);
        btnDic1 = findViewById(R.id.btnDic);
        btnPayOder = findViewById(R.id.btnPayOder);

        oderName = getIntent().getStringExtra("oderName");
        oderPrice = getIntent().getStringExtra("oderPrice");
        tvFO.setText(oderName);
        tvOnePrice.setText(oderPrice);
        tvFOTotal.setText(oderPrice);
        tvFullTot.setText(oderPrice);
        String p1 = tvOnePrice.getText().toString().trim();
        String price = p1.replace("\"", "");
        Log.e("src", "Total value..." + price);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _counter++;
                int cp = (int) Double.parseDouble(price);
                int val = _counter;
                tvFOTotal.setText(String.valueOf((val * cp)));
                tvFullTot.getVisibility();
                tvFullTot.setText(String.valueOf((val * cp)));

                Log.e("src", "Increasing value..." + _stringVal);
            }
        });

        btnDic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _counter--;
                int cp1 = (int) Double.parseDouble(price);
                int val2 = _counter;
                String tot = String.valueOf((val2 * cp1) - cp1);
                tvFOTotal.setText(tot);
                tvFullTot.getVisibility();
                tvFullTot.setText(tot);
            }
        });

        btnPayOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderdetails.this, PwymentMathordActivity.class);
                totAmount = tvFullTot.getText().toString();
                Log.e("src", "Amount.." + totAmount);

                intent.putExtra("totalAmount", totAmount);

                startActivity(intent);
                finish();
            }
        });
    }
}