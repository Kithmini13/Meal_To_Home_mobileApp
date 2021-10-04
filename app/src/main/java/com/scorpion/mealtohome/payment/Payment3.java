package com.scorpion.mealtohome.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;

public class Payment3 extends AppCompatActivity {

    Context mContext;
    EditText etOwnerName,etCardNo,etCardCode,etExDate;
    TextView tvCA,tvA5;
    Button btnPay,btnCancelPay;
    String totAmount,amount,cardType,cardTypes;
    String name,no,pin,date;
   static private ProgressDialog loadingBar;
    ImageView imageView13,imageView14,imageView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment3);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        etOwnerName = findViewById(R.id.etOwnerName);
        etCardNo = findViewById(R.id.etCardNo);
        etCardCode = findViewById(R.id.etCardCode);
        etExDate = findViewById(R.id.etExDate);
        btnPay = findViewById(R.id.btnPay);
        btnCancelPay = findViewById(R.id.btnCancelPay);
        tvA5 = findViewById(R.id.tvA5);
        tvCA = findViewById(R.id.tvCA);
        imageView13 = findViewById(R.id.imageButton13);
        imageView14 = findViewById(R.id.imageButton14);
        imageView15 = findViewById(R.id.imageButton15);


        loadingBar = new ProgressDialog(this);

        amount = getIntent().getStringExtra("totalAmount");
        tvA5.setText(amount);

        cardType = getIntent().getStringExtra("cardType");
        tvCA.setText(cardType);
        Log.e("src", "radioBtnCheck.." + cardType);


        btnCancelPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment3.this, PwymentMathordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentForOrder();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment3.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment3.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment3.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void paymentForOrder() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"Payment Process has Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSignUpSuccess();
        }
    }

    private void onSignUpSuccess() {
        loadingBar.setTitle("Payment Ready Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        processPayment();
    }

    private void processPayment() {
        Intent intent = new Intent(Payment3.this, Payment4.class);
        totAmount = tvA5.getText().toString();
        cardTypes = tvCA.getText().toString();
        intent.putExtra("totalAmount", totAmount);
        intent.putExtra("cardType", cardTypes);
        startActivity(intent);
        loadingBar.dismiss();
        finish();
    }

    private void initialize() {
        name = etOwnerName.getText().toString();
        no = etCardNo.getText().toString();
        pin = etCardCode.getText().toString();
        date = etExDate.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if(name.isEmpty() || name.length()>20){
            etOwnerName.setError("Please Enter Valid Full Name");
            valid =false;
        }if(date.isEmpty() || date.length()>10){
            etExDate.setError("Please Check Date");
            valid =false;
        }if(no.isEmpty() || no.length()>19){
            etCardNo.setError("Please Enter Valid Card No");
            valid =false;
        }if(pin.length() != 3){
            etCardCode.setError("Please Enter Valid Pin No");
            valid =false;
        }
        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(mContext, Payment2.class));

                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}