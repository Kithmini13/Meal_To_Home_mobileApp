package com.scorpion.mealtohome.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.payment.Payment1;

public class Delivery2 extends AppCompatActivity {

    TextView tvMobile,tvName,tvAdd1,tvAdd2;
    DatabaseReference databaseReference;
    Button btnMakePayment;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery2);

        reference =database.getReference("Deliver");

        tvMobile = findViewById(R.id.tvMobile);
        tvName = findViewById(R.id.tvName);
        tvAdd1 = findViewById(R.id.tvAdd1);
        tvAdd2 = findViewById(R.id.tvAdd2);
        btnMakePayment = findViewById(R.id.btnMakePayment);

        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Delivery2.this, Payment1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                    }else {
                        Toast.makeText(Delivery2.this, "This record Not exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Delivery2.this, "No Read Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}