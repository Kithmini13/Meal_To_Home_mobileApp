package com.scorpion.mealtohome.feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.RagisterActivity;
import com.scorpion.mealtohome.UpdateProfileActivity;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.delivery.Delivery2;
import com.scorpion.mealtohome.login.Login3;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.order.Orderdetails;
import com.scorpion.mealtohome.payment.Payment1;
import com.scorpion.mealtohome.payment.Payment4;

import java.util.HashMap;


public class LeaveAFeedback extends AppCompatActivity {
    Context mContext;
    TextView tvContactUs,tvRatingCount,tvA6;
    Button btnFeedback,btnCln;
    EditText etFeedbackMsg;
    RatingBar ratingBarDelivery;
    String amount,amounts,rateCount,feedback;
    int i=0;
    ImageView imageView13,imageView11,imageView12;

    private ProgressDialog loadingBar;

    private  String parentDBName = "Rate";
    private FirebaseAuth firebaseAuth;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_afeedback);


        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        mContext = this;
        ratingBarDelivery = findViewById(R.id.ratingBar);
        tvContactUs = findViewById(R.id.tvContactUs);
        tvRatingCount = findViewById(R.id.tvRatingCount);
        btnFeedback = findViewById(R.id.btnFeedback);
        etFeedbackMsg = findViewById(R.id.etFeedbackMsg);
        btnCln = findViewById(R.id.btnCln);
        tvA6 = findViewById(R.id.tvA6);

        imageView13 = findViewById(R.id.imageView13);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);

        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        mContext= this;

        amount = getIntent().getStringExtra("totalAmount");
        tvA6.setText(amount);

        //finding the specific RatingBar with its unique ID
        LayerDrawable stars=(LayerDrawable)ratingBarDelivery.getProgressDrawable();

        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        ratingBarDelivery.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingBarDelivery.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int)starsf + 1;
                    ratingBarDelivery.setRating(stars);
                    tvRatingCount.setText("You Rated :"+(ratingBarDelivery.getRating()));

                    Toast.makeText(LeaveAFeedback.this, String.valueOf("test"), Toast.LENGTH_SHORT).show();
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return false;
            }
        });

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LeaveAFeedback.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });

        btnCln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LeaveAFeedback.this, Payment4.class);
                startActivity(intent);
                finish();
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeedback();
            }
        });

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LeaveAFeedback.this, Menu1.class);
                startActivity(intent);
                finish();
            }
        });imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LeaveAFeedback.this, Orderdetails.class);
                startActivity(intent);
                finish();
            }
        });imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LeaveAFeedback.this, ContactUs.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addFeedback() {
        initialize();

        if(!validate()){
            Toast.makeText(this,"Feedback is Failed",Toast.LENGTH_SHORT).show();
        }else {
            onRateSuccess();
        }
    }

    private void onRateSuccess() {
        loadingBar.setTitle("Register Now!");
        loadingBar.setMessage("Please Wait, While we are Checking the Credentials!..");
        loadingBar.show();

        validateAmountRate(amounts,feedback,rateCount);
    }

    private void validateAmountRate(String amounts, String feedback, String rateCount) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Rate").child(String.valueOf(i++)).exists())){
                    HashMap<String, Object> customerMap = new HashMap<>();
                    customerMap.put("amounts", amounts);
                    customerMap.put("feedback", feedback);
                    customerMap.put("rateCount", rateCount);

                    RootRef.child("Rate").child(String.valueOf(i++)).updateChildren(customerMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(mContext, "Congratulation your Feedback is Successful!!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                startActivity(new Intent(mContext, ContactUs.class));
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(mContext, "Network Error Please try Again after some Time!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(mContext, "This"+String.valueOf(i)+ " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(mContext, "Please try again", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(mContext, Login3.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initialize() {
        amounts = tvA6.getText().toString();
        rateCount = tvRatingCount.getText().toString();
        feedback = etFeedbackMsg.getText().toString();
    }

    private boolean validate() {
        boolean valid = true;

        if(feedback.isEmpty() || feedback.length()>50){
            etFeedbackMsg.setError("Please Enter Feedback Massage");
            valid =false;
        }if(rateCount.isEmpty()){
            etFeedbackMsg.setError("Please Add Rate");
            valid =false;
        }
        return valid;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(LeaveAFeedback.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}