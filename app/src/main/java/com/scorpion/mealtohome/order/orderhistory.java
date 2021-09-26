package com.scorpion.mealtohome.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.Model.Takeaway;
import com.scorpion.mealtohome.MyAdapter;
import com.scorpion.mealtohome.MyTakewayAdapter;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ThankYouScreen;
import com.scorpion.mealtohome.payment.Payment1;
import com.scorpion.mealtohome.payment.Payment2;

import java.util.ArrayList;

public class orderhistory extends AppCompatActivity {

    RecyclerView rvTakeaway;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference database;
    public MyTakewayAdapter myAdapter;
    ArrayList<Takeaway> list;
    Button btnUpdateMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);

        btnUpdateMenu = findViewById(R.id.btnUpdateMenu);
        rvTakeaway = (RecyclerView) findViewById(R.id.rvTakeaway);
        rvTakeaway.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvTakeaway.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference("Takeaway");
        rvTakeaway.setHasFixedSize(true);
        rvTakeaway.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadList();
        myAdapter = new MyTakewayAdapter(orderhistory.this, list) {
            @Override
            public void updateList() {
                loadList();
                myAdapter.notifyDataSetChanged();
                rvTakeaway.setAdapter(myAdapter);


            }
        };
        rvTakeaway.setAdapter(myAdapter);

        btnUpdateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(orderhistory.this, ThankYouScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadList() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getValue() != null) {
                        System.out.println(dataSnapshot.getValue());
                        Takeaway takeaway = dataSnapshot.getValue(Takeaway.class);
                        takeaway.setId(dataSnapshot.getKey());
                        list.add(takeaway);
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