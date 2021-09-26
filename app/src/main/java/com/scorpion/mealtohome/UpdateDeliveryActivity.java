package com.scorpion.mealtohome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.Delivery;
import com.scorpion.mealtohome.Model.Menu;
import com.scorpion.mealtohome.Model.Order;
import com.scorpion.mealtohome.menu.Menu3;

import java.util.ArrayList;

public class UpdateDeliveryActivity extends AppCompatActivity {

    RecyclerView rvDelivery;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference database;
    public MyDeliveryUpdateAdapter myAdapter;
    ArrayList<Delivery> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delivery);
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        rvDelivery = (RecyclerView) findViewById(R.id.rvDelivery);
        rvDelivery.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvDelivery.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference("Deliver");
        rvDelivery.setHasFixedSize(true);
        rvDelivery.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadList();
        myAdapter = new MyDeliveryUpdateAdapter(UpdateDeliveryActivity.this, list) {
            @Override
            public void updateList() {
                loadList();
                myAdapter.notifyDataSetChanged();
                rvDelivery.setAdapter(myAdapter);
            }
        };
        rvDelivery.setAdapter(myAdapter);
    }

    private void loadList() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getValue() != null) {
                        System.out.println(dataSnapshot.getValue());
                        Delivery order = dataSnapshot.getValue(Delivery.class);
                        order.setId(dataSnapshot.getKey());
                        list.add(order);
                    }
                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}