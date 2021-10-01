package com.scorpion.mealtohome.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.Model.Menu;
import com.scorpion.mealtohome.MyAdapter;
import com.scorpion.mealtohome.MyMenuAdapter;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.contactus.ThankYouScreen;

import java.util.ArrayList;

public class Menu3 extends AppCompatActivity {

    RecyclerView rvMenu;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference database;
    public MyMenuAdapter myAdapter;
    ArrayList<Menu> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        rvMenu = (RecyclerView) findViewById(R.id.rvMenu);
        rvMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference("Menu");
        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadList();
        myAdapter = new MyMenuAdapter(Menu3.this, list) {
            @Override
            public void updateList() {
                loadList();
                myAdapter.notifyDataSetChanged();
                rvMenu.setAdapter(myAdapter);


            }
        };
        rvMenu.setAdapter(myAdapter);
    }

    private void loadList() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getValue() != null) {
                        System.out.println(dataSnapshot.getValue());
                        Menu menu = dataSnapshot.getValue(Menu.class);
                        menu.setId(dataSnapshot.getKey());
                        list.add(menu);
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
                startActivity(new Intent(Menu3.this, Menu1.class));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}