package com.scorpion.mealtohome.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.payment.Payment1;

public class Delivery2 extends AppCompatActivity {

    TextView tvMobile,tvName,tvAdd1,tvAdd2,tvA2;
    DatabaseReference databaseReference;
    Button btnMakePayment;
    String amount,totAmount;
//    FirebaseDatabase database;
    DatabaseReference reference;
    DocumentReference documentReference;
    ImageView imageView13,imageView11,imageView12;

//    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DatabaseReference database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery2);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

//        database = FirebaseDatabase.getInstance();
//        reference =database.getReference("Deliver");

        tvMobile = findViewById(R.id.tvMobile);
        tvName = findViewById(R.id.tvName);
        tvAdd1 = findViewById(R.id.tvAdd1);
        tvAdd2 = findViewById(R.id.tvAdd2);
        btnMakePayment = findViewById(R.id.btnMakePayment);
        tvA2 = findViewById(R.id.tvA2);
        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

        amount = getIntent().getStringExtra("totalAmount");
        tvA2.setText(amount);


        database = FirebaseDatabase.getInstance().getReference("Deliver").child(getIntent().getStringExtra("oderID"));

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tvMobile.setText(snapshot.child("cusNo").getValue().toString());
                    tvName.setText(snapshot.child("cusName").getValue().toString());
                    tvAdd1.setText(snapshot.child("add1").getValue().toString());
                    tvAdd2.setText(snapshot.child("add2").getValue().toString());
                }else {
                    Log.e("Tag","massage"+"No snapshot");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery2.this, Payment1.class);
                totAmount = tvA2.getText().toString();
                intent.putExtra("totalAmount", totAmount);
                startActivity(intent);
                finish();
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery2.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery2.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery2.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });

        //getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String value = snapshot.getValue(String.class);
                Log.e("Tag","Values"+value);
//                tvName.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(Delivery2.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()){
//                    if (task.getResult().exists()){
//
//                    }else {
//                        Toast.makeText(Delivery2.this, "This record Not exists", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(Delivery2.this, "No Read Data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(Delivery2.this, Delivery1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}