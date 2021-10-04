package com.scorpion.mealtohome.delivery;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.scorpion.mealtohome.Model.Order;
import com.scorpion.mealtohome.PwymentMathordActivity;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateDeliveryActivity;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.order.orderhistory;
import com.scorpion.mealtohome.order.takeaway;
import com.scorpion.mealtohome.payment.Payment1;

import java.util.ArrayList;
import java.util.HashMap;

public class Delivery1 extends AppCompatActivity {
    Context mContext;
    EditText etPersonName,etAddress1,etAddress2,etContact,etOrderId;
    Button btnSubmit,btnCancel,btnUpdateDelivery;
    private ProgressDialog loadingBar;
    TextView tvA1;
    String cusDEName,delAdd1,delAdd2,cusNo,orderId,totAmount,amount;
    private DatabaseReference database;
    private  String parentDBName = "Deliver";
    private FirebaseAuth firebaseAuth;
    ImageView imageView13,imageView11,imageView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery1);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        etPersonName = findViewById(R.id.etPersonName);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etContact = findViewById(R.id.etContact);
        etOrderId = findViewById(R.id.etOrderId);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdateDelivery = findViewById(R.id.btnUpdateDelivery);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        tvA1 = findViewById(R.id.tvA1);
        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        amount = getIntent().getStringExtra("totalAmount");
        tvA1.setText(amount);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliveryOder();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery1.this, PwymentMathordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnUpdateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery1.this, UpdateDeliveryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery1.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery1.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery1.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void DeliveryOder() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"Delivery Details has Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSignUpSuccess();
        }
    }

    private void onSignUpSuccess() {
        loadingBar.setTitle("Order Ready Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        validateDeliveryOderCusNo(orderId,cusDEName,delAdd1,delAdd2,cusNo);

    }

    private void validateDeliveryOderCusNo(String orderId,String cusDEName, String delAdd1, String delAdd2, String cusNo) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Deliver").child(orderId).exists())){
                    HashMap<String, Object> deliveryMap = new HashMap<>();
                    deliveryMap.put("orderID", orderId);
                    deliveryMap.put("cusNo", cusNo);
                    deliveryMap.put("cusName", cusDEName);
                    deliveryMap.put("add1", delAdd1);
                    deliveryMap.put("add2", delAdd2);

                    RootRef.child("Deliver").child(orderId).updateChildren(deliveryMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Delivery1.this, "Congratulation your Order is Successful!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent=new Intent(mContext, Delivery2.class);
                                totAmount = tvA1.getText().toString();
                                intent.putExtra("totalAmount", totAmount);
                                startActivity(intent);
                                finish();
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(Delivery1.this, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Delivery1.this, "This"+etOrderId+ " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Delivery1.this, "Thank You!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initialize() {
        orderId = etOrderId.getText().toString();
        cusDEName = etPersonName.getText().toString();
        delAdd1 = etAddress1.getText().toString();
        delAdd2 = etAddress2.getText().toString();
        cusNo = etContact.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if(orderId.isEmpty() || orderId.length()>10){
            etOrderId.setError("Please Enter Valid Oder ID No");
            valid =false;
        }if(cusDEName.isEmpty() || cusDEName.length()>20){
            etPersonName.setError("Please Enter Valid Full Name");
            valid =false;
        }if(delAdd1.isEmpty() || delAdd1.length()>30){
            etAddress1.setError("Please Enter Valid Address Line 1");
            valid =false;
        }if(delAdd2.isEmpty() || delAdd2.length()>30){
            etAddress2.setError("Please Enter Valid Address Line 2");
            valid =false;
        }if(cusNo.length() != 10){
            etContact.setError("Please Enter Valid Contact Number");
            valid =false;
        }
        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(mContext, PwymentMathordActivity.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}