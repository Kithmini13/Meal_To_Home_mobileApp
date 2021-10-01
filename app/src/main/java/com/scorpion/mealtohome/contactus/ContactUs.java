package com.scorpion.mealtohome.contactus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.feedback.LeaveAFeedback;
import com.scorpion.mealtohome.login.Login3;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;

import java.util.HashMap;


public class ContactUs extends AppCompatActivity {

    Context mContext;
    EditText etContactName, etContactEmail, etContactMsg;
    Button btnSendMsg;
    int i=0;
    String name, email, massage;
    ImageView imageView11,imageView12,imageView13,imageView14;

    private ProgressDialog loadingBar;

    private String parentDBName = "ContactUs";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        etContactName = findViewById(R.id.etContactName);
        etContactEmail = findViewById(R.id.etContactEmail);
        etContactMsg = findViewById(R.id.etContactMsg);
        btnSendMsg = findViewById(R.id.btnSendMsg);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);

        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendContactUs();
            }
        });

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactUs.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactUs.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(ContactUs.this, UpdateProfileActivity.class);
//                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
//                startActivity(intent);
//                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactUs.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendContactUs() {
        initialize();

        if (!validate()) {
            Toast.makeText(this, "ContactUs Details Sending Failed", Toast.LENGTH_SHORT).show();
        } else {
            onContactUs(name, email, massage);
        }
    }

    private void onContactUs(String name, String email, String massage) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("ContactUs").child(name).exists())) {
                    HashMap<String, Object> customerMap = new HashMap<>();
                    customerMap.put("name", name);
                    customerMap.put("email", email);
                    customerMap.put("massage", massage);

                    RootRef.child("ContactUs").child(String.valueOf(i++)).updateChildren(customerMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                                dialog.setCancelable(false);
                                dialog.setTitle("Thank You !!");
                                dialog.setMessage("We will be in touch Shortly...");
                                dialog.setPositiveButton("Offers", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivity(new Intent(mContext, ThankYouScreen.class));
                                    }
                                })
                                        .setNegativeButton("Ok ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(mContext, Menu1.class));
                                            }
                                        }).setNegativeButton("Update Details", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(mContext, ContactUs.class));
                                    }
                                });

                                final AlertDialog alert = dialog.create();
                                alert.show();
                                Toast.makeText(mContext, "Congratulation your Massage is Successfully Send!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            } else {
                                loadingBar.dismiss();
                                Toast.makeText(mContext, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(mContext, "This" + String.valueOf(i) + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(mContext, "Please try again", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(mContext, ContactUs.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initialize() {
        name = etContactName.getText().toString();
        email = etContactEmail.getText().toString();
        massage = etContactMsg.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if (name.isEmpty() || name.length() > 20) {
            etContactName.setError("Please Enter Name");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etContactEmail.setError("Please Enter Valid Email");
            valid = false;
        }
        if (massage.isEmpty() || name.length() > 50) {
            etContactMsg.setError("Please Add Massage");
            valid = false;
        }
        return valid;
    }

    public void submitForm(View v) {
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