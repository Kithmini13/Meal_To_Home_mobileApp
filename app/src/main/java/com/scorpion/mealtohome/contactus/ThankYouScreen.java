package com.scorpion.mealtohome.contactus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.MyAdapter;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.menu.Menu1;

import java.util.ArrayList;

public class ThankYouScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    //    FirebaseRecyclerAdapter<Category, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference database;
    public MyAdapter myAdapter;
    ArrayList<Category> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_screen);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference("Category");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadList();
        myAdapter = new MyAdapter(ThankYouScreen.this, list) {
            @Override
            public void updateList() {
                loadList();
                myAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(myAdapter);


            }
        };
        recyclerView.setAdapter(myAdapter);
    }

    private void loadList() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getValue() != null) {
                        System.out.println(dataSnapshot.getValue());
                        Category category = dataSnapshot.getValue(Category.class);
                        category.setId(dataSnapshot.getKey());
                        list.add(category);
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
                startActivity(new Intent(ThankYouScreen.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}