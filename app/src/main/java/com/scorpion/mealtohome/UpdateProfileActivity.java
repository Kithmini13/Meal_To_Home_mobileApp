package com.scorpion.mealtohome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText etRegFullName, etRegEmail, etRegPhoneNo, etRegPassword;
    Button btnUpdate, btnDelete;
    String uid;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        documentReference = db.collection("Customer").document(uid);

        etRegFullName = findViewById(R.id.etFullNameUp);
        etRegEmail = findViewById(R.id.etMailUp);
        etRegPhoneNo = findViewById(R.id.etMobile6Up);
        etRegPassword = findViewById(R.id.etPasswordsUp);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDeletePro);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    String fullName = task.getResult().getString("fullName");
                    String phone = task.getResult().getString("phone");
                    String email = task.getResult().getString("email");
                    String password = task.getResult().getString("password");

                    etRegFullName.setText(fullName);
                    etRegPhoneNo.setText(phone);
                    etRegEmail.setText(email);
                    etRegPassword.setText(password);
                } else {
                    Toast.makeText(UpdateProfileActivity.this, "No Profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateProfile() {
        String name = etRegFullName.getText().toString();
        String phone = etRegPhoneNo.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        final DocumentReference sDoc = db.collection("Customer").document(uid);

        db.runTransaction(new com.google.firebase.firestore.Transaction.Function<Object>() {
            @Nullable
            @Override
            public Object apply(@NonNull com.google.firebase.firestore.Transaction transaction) throws FirebaseFirestoreException {

                transaction.update(sDoc,"fullName",name);
                transaction.update(sDoc,"phone",phone);
                transaction.update(sDoc,"email",email);
                transaction.update(sDoc,"password",password);

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Object>() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateProfileActivity.this, "Profile Update Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}