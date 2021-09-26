package com.scorpion.mealtohome;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.mealtohome.Model.Category;
import com.scorpion.mealtohome.Model.Takeaway;
import com.scorpion.mealtohome.contactus.ThankYouScreen;
import com.scorpion.mealtohome.order.orderhistory;

import java.util.ArrayList;

public abstract class MyTakewayAdapter extends RecyclerView.Adapter<MyTakewayAdapter.MyViewHolder> {
    orderhistory context;
    ArrayList<Takeaway> list;
    private DatabaseReference database;


    public MyTakewayAdapter(@NonNull orderhistory context, ArrayList<Takeaway> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyTakewayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_takeway_view, parent, false);
        return new MyTakewayAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTakewayAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Takeaway takeaway = list.get(position);
        holder.cusID.setText(takeaway.getCusID());
        holder.cusName.setText(takeaway.getCusTWName());
        holder.cusTime.setText(takeaway.getCusTime());
        holder.btnDeleteTakeaway.setOnClickListener(v -> {

            database = FirebaseDatabase.getInstance().getReference("Takeaway");
            database.child(takeaway.getId()).removeValue(new DatabaseReference.CompletionListener() {
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

        TextView cusName, cusTime,cusID;
        Button btnDeleteTakeaway;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cusName = itemView.findViewById(R.id.textView20);
            cusTime = itemView.findViewById(R.id.textView21);
            btnDeleteTakeaway = itemView.findViewById(R.id.btnDeleteTakeaway);
            cusID = itemView.findViewById(R.id.textView22);

        }
    }
}
