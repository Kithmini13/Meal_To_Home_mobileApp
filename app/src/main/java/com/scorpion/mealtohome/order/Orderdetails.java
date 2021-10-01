package com.scorpion.mealtohome.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery2;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.menu.Menu1Chines;

//order
public class Orderdetails extends AppCompatActivity {

    TextView tvFO, tvFOTotal, tvOnePrice, tvFullTot;
    Button btnAdd1,btnDic1,btnPayOder;
    String oderName, oderPrice,totAmount;
    private static int _counter = 1;
    private String _stringVal;
    ImageView imageView13,imageView11,imageView12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvFO = findViewById(R.id.textView7);
        tvFOTotal = findViewById(R.id.textView9);
        tvOnePrice = findViewById(R.id.textView8);
        tvFullTot = findViewById(R.id.tvFullTot);
        btnAdd1 = findViewById(R.id.button);
        btnDic1 = findViewById(R.id.btnDic);
        btnPayOder = findViewById(R.id.btnPayOder);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

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

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderdetails.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderdetails.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderdetails.this, ContactUs.class);
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
                startActivity(new Intent(Orderdetails.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}