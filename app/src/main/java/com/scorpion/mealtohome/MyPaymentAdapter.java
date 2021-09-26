package com.scorpion.mealtohome;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.mealtohome.Model.PaymentType;
import com.scorpion.mealtohome.Model.Takeaway;
import com.scorpion.mealtohome.order.orderhistory;
import com.scorpion.mealtohome.payment.PaymentTypeHitory;

import java.util.ArrayList;

public abstract class MyPaymentAdapter extends RecyclerView.Adapter<MyPaymentAdapter.MyViewHolder> {
    PaymentTypeHitory context;
    ArrayList<PaymentType> list;
    private DatabaseReference database;

    public MyPaymentAdapter(@NonNull PaymentTypeHitory context, ArrayList<PaymentType> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyPaymentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_payment_view, parent, false);
        return new MyPaymentAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPaymentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PaymentType paymentType = list.get(position);
        holder.cardType.setText(paymentType.getCardType());
        holder.cusName.setText(paymentType.getCardOwnerName());
//        holder.cusTime.setText(paymentType.getPayDate());
        holder.tvCardAmount.setText(paymentType.getPayAmount());
        holder.btnDel.setOnClickListener(v -> {

            database = FirebaseDatabase.getInstance().getReference("Takeaway");
            database.child(paymentType.getId()).removeValue(new DatabaseReference.CompletionListener() {
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

        TextView cusName, cusTime,cardType,tvTime,tvCardAmount;
        Button btnDel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cusName = itemView.findViewById(R.id.tvOwnerName);
            cardType = itemView.findViewById(R.id.tvCardType);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCardAmount = itemView.findViewById(R.id.tvCardAmount);
            btnDel = itemView.findViewById(R.id.btnDel);

        }
    }
}
