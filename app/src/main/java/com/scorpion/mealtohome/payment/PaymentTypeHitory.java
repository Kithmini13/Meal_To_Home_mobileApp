package com.scorpion.mealtohome.payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.PaymentType;
import com.scorpion.mealtohome.Model.Takeaway;
import com.scorpion.mealtohome.MyPaymentAdapter;
import com.scorpion.mealtohome.MyTakewayAdapter;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.order.orderhistory;

import java.util.ArrayList;

public class PaymentTypeHitory extends AppCompatActivity {

    RecyclerView rvPaymentHistory;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference database;
    public MyPaymentAdapter myAdapter;
    ArrayList<PaymentType> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_type_hitory);

        rvPaymentHistory = (RecyclerView) findViewById(R.id.rvPaymentHistory);
        rvPaymentHistory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvPaymentHistory.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference("Payment");
        rvPaymentHistory.setHasFixedSize(true);
        rvPaymentHistory.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadList();
        myAdapter = new MyPaymentAdapter(PaymentTypeHitory.this, list) {
            @Override
            public void updateList() {
                loadList();
                myAdapter.notifyDataSetChanged();
                rvPaymentHistory.setAdapter(myAdapter);


            }
        };
        rvPaymentHistory.setAdapter(myAdapter);
    }

    private void loadList() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getValue() != null) {
                        System.out.println(dataSnapshot.getValue());
                        PaymentType paymentType = dataSnapshot.getValue(PaymentType.class);
                        paymentType.setId(dataSnapshot.getKey());
                        list.add(paymentType);
                    }
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}