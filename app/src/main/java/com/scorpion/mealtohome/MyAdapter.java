package com.scorpion.mealtohome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.contactus.ThankYouScreen;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ThankYouScreen context;
    ArrayList<Category> list;
    private DatabaseReference database;

    // Test
    public MyAdapter(@NonNull ThankYouScreen context, ArrayList<Category> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Category category = list.get(position);
        holder.categoryName.setText(category.getcName());
        holder.cPrice.setText(String.valueOf(category.getPrice()));
        holder.btnDeleteOfer.setOnClickListener(v -> {

            database = FirebaseDatabase.getInstance().getReference("Category");
            database.child(category.getId()).removeValue(new DatabaseReference.CompletionListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                    this.context.myAdapter.notifyDataSetChanged();
                    removeAt(position);
                    updateList();

                }
            });
        });
    }
    public void removeAt(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
    public abstract void updateList();

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName, cPrice;
        ImageView img;
        Button btnDeleteOfer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.tvCategory);
            cPrice = itemView.findViewById(R.id.tvPrice);
            btnDeleteOfer = itemView.findViewById(R.id.btnDeleteOfer);
            img = itemView.findViewById(R.id.img);

        }
    }
}
