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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery1;
import com.scorpion.mealtohome.login.Login3;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.order.orderhistory;
import com.scorpion.mealtohome.order.takeaway;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Payment2 extends AppCompatActivity {
    Context mContext;
    Button btnPaymentSubmit;
    TextView tvAmount,tvDate,tvA4;
    EditText etCardOwnerName;
    RadioButton radioBtnMaster,radioBtnVisa;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    String date,amount,cusCOwnerName,payDate,payAmount,radioBtnCheck,totAmount;

    private ProgressDialog loadingBar;

    private  String parentDBName = "Payment";
    private FirebaseAuth firebaseAuth;
    ImageView imageView13,imageView14,imageView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment2);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        tvAmount = findViewById(R.id.etAmount);
        tvA4 = findViewById(R.id.tvA4);
        etCardOwnerName = findViewById(R.id.etCardOwnerName);
        tvDate = findViewById(R.id.etDate);
        btnPaymentSubmit = findViewById(R.id.btnPaymentSubmit);
        radioBtnMaster = findViewById(R.id.radioBtnMaster);
        radioBtnVisa = findViewById(R.id.radioBtnVisa);
        imageView13 = findViewById(R.id.imageButton13);
        imageView14 = findViewById(R.id.imageButton14);
        imageView15 = findViewById(R.id.imageButton15);

        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        date = dateFormat.format(calendar.getTime());
        tvDate.setText(date);

        amount = getIntent().getStringExtra("totalAmount");
        tvAmount.setText(amount);
        tvA4.setText(amount);

        btnPaymentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioBtnVisa.isChecked()){
                    radioBtnCheck = radioBtnVisa.getText().toString();
                }else if(radioBtnMaster.isChecked()){
                    radioBtnCheck = radioBtnMaster.getText().toString();
                }

                paymentForOrder();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment2.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment2.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Payment2.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void paymentForOrder() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"Payment Details has Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSignUpSuccess();
        }
    }

    private void onSignUpSuccess() {
        loadingBar.setTitle("Order Ready Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        validatePayment(cusCOwnerName,payDate,payAmount,radioBtnCheck);

    }

    private void validatePayment(final String cusCOwnerName,final String payDate,final String payAmount,final String radioBtnCheck) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Payment").child(cusCOwnerName).exists())){
                    HashMap<String, Object> paymentMap = new HashMap<>();
                    paymentMap.put("cardOwnerName", cusCOwnerName);
                    paymentMap.put("payDate", payDate);
                    paymentMap.put("payAmount", payAmount);
                    paymentMap.put("cardType", radioBtnCheck);

                    RootRef.child("Payment").child(cusCOwnerName).updateChildren(paymentMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Payment2.this, "Congratulation your Payment Process is Successful!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent=new Intent(mContext, Payment3.class);
                                totAmount = tvA4.getText().toString();
                                intent.putExtra("totalAmount", totAmount);
                                intent.putExtra("cardType", radioBtnCheck);

                                startActivity(intent);
                                finish();
//                                startActivity(new Intent(mContext, Payment3.class));
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(Payment2.this, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Payment2.this, "This"+etCardOwnerName+ " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Payment2.this, "Please Enter Your Correct Owner Name", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(mContext, takeaway.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initialize() {
        cusCOwnerName = etCardOwnerName.getText().toString();
        payDate = tvDate.getText().toString();
        payAmount = tvAmount.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if(cusCOwnerName.isEmpty() || cusCOwnerName.length()>20){
            etCardOwnerName.setError("Please Enter Valid Full Name");
            valid =false;
        }if(payDate.isEmpty()){
            tvDate.setError("Please Check Date and Time");
            valid =false;
        }
        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(mContext, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}