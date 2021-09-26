package com.scorpion.mealtohome;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scorpion.mealtohome.Model.Delivery;
import com.scorpion.mealtohome.Model.Menu;
import com.scorpion.mealtohome.Model.Order;
import com.scorpion.mealtohome.menu.Menu3;

import java.util.ArrayList;

public abstract class MyDeliveryUpdateAdapter extends RecyclerView.Adapter<MyDeliveryUpdateAdapter.MyViewHolder> {

    UpdateDeliveryActivity context;
    ArrayList<Delivery> list;
    private DatabaseReference database;


    public MyDeliveryUpdateAdapter(@NonNull UpdateDeliveryActivity context, ArrayList<Delivery> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_delivery_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Delivery order = list.get(position);
        holder.etOrderId1.setText(String.valueOf(order.getOrderID()));
        holder.etPersonName1.setText(order.getCusName());
        holder.etAddress11.setText(String.valueOf(order.getAdd1()));
        holder.etAddress21.setText(String.valueOf(order.getAdd2()));
        holder.etContact1.setText(String.valueOf(order.getCusNo()));

        holder.btnUpdateDelivery1.setOnClickListener(v -> {

            database = FirebaseDatabase.getInstance().getReference("Deliver");
            Delivery order1 =new Delivery();
            order1.setOrderID(holder.etOrderId1.getText().toString());
            order1.setCusName(holder.etPersonName1.getText().toString());
            order1.setAdd1(holder.etAddress11.getText().toString());
            order1.setAdd2(holder.etAddress21.getText().toString());
            order1.setCusNo(holder.etContact1.getText().toString());
            database.child(order.getId()).setValue(order1);

            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setCancelable(false);
            dialog.setTitle("Update");
            dialog.setMessage("Updated Your Order Delivery Details");
            dialog.setNegativeButton("Ok ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

            final AlertDialog alert = dialog.create();
            alert.show();
        });
    }

    public abstract void updateList();

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        EditText etPersonName1,etAddress11,etAddress21,etContact1,etOrderId1;
        Button btnUpdateDelivery1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            etPersonName1 = itemView.findViewById(R.id.etPersonName11);
            etAddress11 = itemView.findViewById(R.id.etAddress111);
            etAddress21 = itemView.findViewById(R.id.etAddress211);
            etContact1 = itemView.findViewById(R.id.etContact1);
            etOrderId1 = itemView.findViewById(R.id.etOrderId11);
            btnUpdateDelivery1 = itemView.findViewById(R.id.btnUpdateDelivery1);

        }
    }
}
