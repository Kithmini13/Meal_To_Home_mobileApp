package com.scorpion.mealtohome.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery2;
import com.scorpion.mealtohome.login.Login3;
import com.scorpion.mealtohome.menu.Menu1;

import java.util.HashMap;

public class takeaway extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context mContext;
    Button btnSubmitTWD;
    EditText etTWName, etTWNo,etTWTime;
    Spinner spTWBranch;
    String cusTWName,cusID,cusTime,branch;
    String dr1;
    ImageView imageView13,imageView11,imageView12;
    private static final String[] paths = {"Colombo Branch", "Kandy Branch", "Kaluthara Branch","Jaffna Branch","Negambo Branch"};

    private ProgressDialog loadingBar;

    private  String parentDBName = "Takeaway";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeaway);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        btnSubmitTWD = findViewById(R.id.btnSubmitTWD);
        spTWBranch = findViewById(R.id.spTWBranch);
        etTWName = findViewById(R.id.etTWName);
        etTWNo = findViewById(R.id.etTWNo);
        etTWTime = findViewById(R.id.etTWTime);

        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

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

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(takeaway.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(takeaway.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(takeaway.this, ContactUs.class);
                startActivity(intent);
                finish();
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


        validateOderId(cusTWName,cusID,cusTime,branch);

    }

    private void validateOderId(final String cusTWName, final String cusID, final String cusTime,String branch) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Takeaway").child(cusID).exists())){
                    HashMap<String, Object> takeawayMap = new HashMap<>();
                    takeawayMap.put("cusID", cusID);
                    takeawayMap.put("branch", branch);
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
        branch = spTWBranch.getSelectedItem().toString();
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
//
//        switch (i){
//            case 0:
//               dr1  = "Colombo Branch";
//                break;
//
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(takeaway.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}