package com.scorpion.mealtohome.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery1;
import com.scorpion.mealtohome.feedback.LeaveAFeedback;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Payment4 extends AppCompatActivity {

    TextView tvSDate,tvType,tvPayAmount;
    String totAmount,amount,cardType;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date1;
    Button btnRatePage;
    ImageView imageView13,imageView14,imageView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment4);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        tvSDate = findViewById(R.id.tvSDate);
        tvType = findViewById(R.id.tvType);
        tvPayAmount = findViewById(R.id.tvPayAmount);
        btnRatePage = findViewById(R.id.btnRatePage);
        imageView13 = findViewById(R.id.imageButton13);
        imageView14 = findViewById(R.id.imageButton14);
        imageView15 = findViewById(R.id.imageButton15);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        date1 = dateFormat.format(calendar.getTime());
        tvSDate.setText(date1);

        amount = getIntent().getStringExtra("totalAmount");
        tvPayAmount.setText(amount);

        cardType = getIntent().getStringExtra("cardType");
        tvType.setText(cardType);

        btnRatePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Payment4.this, LeaveAFeedback.class);
                totAmount = tvPayAmount.getText().toString();
                intent.putExtra("totalAmount", totAmount);
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment4.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment4.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment4.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Payment4.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}