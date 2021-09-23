package com.scorpion.mealtohome.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.scorpion.mealtohome.RagisterActivity;
import com.scorpion.mealtohome.login.Login3;

import java.util.HashMap;

public class takeaway extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context mContext;
    Button btnSubmitTWD;
    EditText etTWName, etTWNo,etTWTime;
    Spinner spTWBranch;
    String cusTWName,cusID,cusTime;
    private static final String[] paths = {"Colombo Branch", "Kandy Branch", "Kaluthara Branch","Jaffna Branch","Negambo Branch"};

    private ProgressDialog loadingBar;

    private  String parentDBName = "Takeaway";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeaway);

        mContext = this;
        btnSubmitTWD = findViewById(R.id.btnSubmitTWD);
        spTWBranch = findViewById(R.id.spTWBranch);
        etTWName = findViewById(R.id.etTWName);
        etTWNo = findViewById(R.id.etTWNo);
        etTWTime = findViewById(R.id.etTWTime);
        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(takeaway.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTWBranch.setAdapter(adapter);
        spTWBranch.setOnItemSelectedListener(this);

        btnSubmitTWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeawayOder();
            }
        });
    }

    private void takeawayOder() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"Takeaway Details has Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSignUpSuccess();
        }
    }

    private void onSignUpSuccess() {
        loadingBar.setTitle("Order Ready Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        validateOderId(cusTWName,cusID,cusTime);

    }

    private void validateOderId(final String cusTWName, final String cusID, final String cusTime) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Takeaway").child(cusID).exists())){
                    HashMap<String, Object> takeawayMap = new HashMap<>();
                    takeawayMap.put("cusID", cusID);
                    takeawayMap.put("cusTWName", cusTWName);
                    takeawayMap.put("cusTime", cusTime);

                    RootRef.child("Takeaway").child(cusID).updateChildren(takeawayMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(takeaway.this, "Congratulation your Order is Successful!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                startActivity(new Intent(mContext, orderhistory.class));
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(takeaway.this, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(takeaway.this, "This"+etTWNo+ " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(takeaway.this, "Please Enter Your Correct Order ID", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(mContext, takeaway.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initialize() {
        cusTWName = etTWName.getText().toString();
        cusID = etTWNo.getText().toString();
        cusTime = etTWTime.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if(cusTWName.isEmpty() || cusTWName.length()>20){
            etTWName.setError("Please Enter Valid Full Name");
            valid =false;
        }if(cusID.isEmpty() || cusID.length()>6){
            etTWNo.setError("Please Enter Valid Order ID");
            valid =false;
        }if(cusTime.isEmpty()){
            etTWTime.setError("Please Enter Valid Time");
            valid =false;
        }
        return valid;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}