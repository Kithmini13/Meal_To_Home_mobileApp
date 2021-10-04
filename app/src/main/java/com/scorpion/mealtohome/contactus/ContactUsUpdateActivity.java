package com.scorpion.mealtohome.contactus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;

import java.util.HashMap;
import java.util.Map;

public class ContactUsUpdateActivity extends AppCompatActivity {

    Context mContext;
    EditText etContactName, etContactEmail, etContactMsg;
    Button btnUpdate;
    ImageView imageView11,imageView12,imageView13,imageView14;

    DatabaseReference database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_update);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        mContext = this;
        etContactName = findViewById(R.id.etContactName);
        etContactEmail = findViewById(R.id.etContactEmail);
        etContactMsg = findViewById(R.id.etContactMsg);
        btnUpdate = findViewById(R.id.btnSendMsg);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);

        database = FirebaseDatabase.getInstance().getReference("ContactUs").child("0");
        Log.e("Tag","Name"+getIntent().getStringExtra("contactName"));
        Log.e("Tag","Name"+database);

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    etContactName.setText(snapshot.child("name").getValue().toString());
                    etContactEmail.setText(snapshot.child("email").getValue().toString());
                    etContactMsg.setText(snapshot.child("massage").getValue().toString());
                }else {
                    Log.e("Tag","massage"+"No snapshot");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateContactUsDetails();
            }
        });

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(mContext, UpdateProfileActivity.class);
//                intent.putExtra("phoneNo",getIntent().getStringExtra("phoneNo"));
//                startActivity(intent);
//                finish();
            }
        });imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateContactUsDetails() {
        String name = etContactName.getText().toString();
        String email = etContactEmail.getText().toString();
        String msg = etContactMsg.getText().toString();
        Map<String,Object> d= new HashMap<>();
        d.put("name",name);
        d.put("email",email);
        d.put("massage",msg);
        database.setValue(d).addOnCompleteListener(task ->
                Toast.makeText(mContext, "ContactUs Details Updated", Toast.LENGTH_SHORT).show());

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setCancelable(false);
        dialog.setTitle("ContactUs Details ");
        dialog.setMessage("ContactUs Details Updated");
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
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    }

    // this event will enable the back
    // function to the button on press
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