package com.scorpion.mealtohome.contactus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.R;
import com.scorpion.mealtohome.ViewHolder.CategoryViewHolder;

public class ThankYouScreen extends AppCompatActivity {
    RecyclerView recyclerView;
//    FirebaseRecyclerAdapter<Category, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_screen);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Category");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //loadData();
    }

//    private void loadData()
//    {
//        FirebaseRecyclerOptions options =
//                new FirebaseRecyclerOptions.Builder<Category>()
//                        .setQuery(databaseReference,Category.class)
//                        .build();
//
//        recyclerAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
//
//                holder.tvCategoryName.setText(model.getCategoryName());
//                holder.tvPrice.setText(model.getPrice());
//
//            }
//
//            @NonNull
//            @Override
//            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//                View view = LayoutInflater.from(viewGroup.getContext())
//                        .inflate(R.layout.item_view,viewGroup,false);
//                return new CategoryViewHolder(view);
//            }
//        };
//        recyclerAdapter.notifyDataSetChanged();
//        recyclerAdapter.startListening();
//        recyclerView.setAdapter(recyclerAdapter);
//    }
}