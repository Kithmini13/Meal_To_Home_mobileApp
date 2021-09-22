package com.scorpion.mealtohome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.login.Login1;
import com.scorpion.mealtohome.login.Login3;

import java.util.HashMap;

public class RagisterActivity extends AppCompatActivity {

    Activity mActivity;
    Context mContext;

    TextView alreadyRegistered;
    EditText etRegFullName, etRegEmail, etRegPhoneNo, etRegPassword;
    String fullName, email, phoneNo, password;
    Button btnRegister;
    private ProgressDialog loadingBar;

    private  String parentDBName = "Customer";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);

        mContext = this;

        etRegFullName = findViewById(R.id.etFullName);
        etRegEmail = findViewById(R.id.etMail);
        etRegPhoneNo = findViewById(R.id.etMobile6);
        etRegPassword = findViewById(R.id.etPasswords);
        btnRegister = findViewById(R.id.btnSignUp);
        alreadyRegistered = findViewById(R.id.tvSignUpNow);
        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                if (validate()){
                    String user_email = etRegEmail.getText().toString().trim();
                    String user_password = etRegPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(RagisterActivity.this, Login3.class));
                            }else {

                            }
                        }
                    });
                }else {

                }
            }
        });

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Login3.class));
            }
        });
    }

    private void register() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"SignUp has Failed",Toast.LENGTH_SHORT).show();
        }else {
            onSignUpSuccess();
        }
    }

    private void initialize() {
        fullName = etRegFullName.getText().toString();
        email = etRegEmail.getText().toString();
        phoneNo = etRegPhoneNo.getText().toString();
        password = etRegPassword.getText().toString();
    }

    private void onSignUpSuccess() {
        loadingBar.setTitle("Register Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        validatePhoneNumber(fullName,email,phoneNo,password);
    }

    private void validatePhoneNumber(final String etRegFullName, final String etRegEmail, final String etRegPhoneNo, final String etRegPassword) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Customer").child(etRegPhoneNo).exists())){
                    HashMap<String, Object> customerMap = new HashMap<>();
                    customerMap.put("phone", etRegPhoneNo);
                    customerMap.put("fullName", etRegFullName);
                    customerMap.put("email", etRegEmail);
                    customerMap.put("password", etRegPassword);

                    RootRef.child("Customer").child(etRegPhoneNo).updateChildren(customerMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RagisterActivity.this, "Congratulation your Registration is Successful!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                startActivity(new Intent(mContext, Login3.class));
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(RagisterActivity.this, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(RagisterActivity.this, "This"+etRegPhoneNo+ " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(RagisterActivity.this, "Please try again using another Phone Number", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(mContext, Login3.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        if(fullName.isEmpty() || fullName.length()>20){
            etRegFullName.setError("Please Enter Valid Full Name");
            valid =false;
        }if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etRegEmail.setError("Please Enter Valid Email");
            valid =false;
        }if(phoneNo.isEmpty() || phoneNo.length()>10){
            etRegPhoneNo.setError("Please Enter Valid Phone Number");
            valid =false;
        }if(password.isEmpty() || password.length()<6){
            etRegPassword.setError("Please Enter Password More than 6 Characters");
            valid =false;
        }

        return valid;
    }
}