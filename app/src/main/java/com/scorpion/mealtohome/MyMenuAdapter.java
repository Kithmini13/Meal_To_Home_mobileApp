package com.scorpion.mealtohome;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.scorpion.mealtohome.Model.Menu;
import com.scorpion.mealtohome.contactus.ContactUs;
import com.scorpion.mealtohome.contactus.ThankYouScreen;
import com.scorpion.mealtohome.menu.Menu1;
import com.scorpion.mealtohome.menu.Menu3;

import java.util.ArrayList;

public abstract class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.MyViewHolder> {

    Menu3 context;
    ArrayList<Menu> list;
    private DatabaseReference database;


    public MyMenuAdapter(@NonNull Menu3 context, ArrayList<Menu> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_menu_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Menu menu = list.get(position);
        holder.categoryName.setText(menu.getcName());
        holder.cPrice.setText(String.valueOf(menu.getPrice()));
        holder.btnUpdateMenu.setOnClickListener(v -> {

            database = FirebaseDatabase.getInstance().getReference("Menu");
            Menu menu1=new Menu();
            menu1.setcName(holder.categoryName.getText().toString());
            menu1.setPrice(Integer.parseInt( holder.cPrice.getText().toString() ) );
            database.child(menu.getId()).setValue(menu1);
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setCancelable(false);
            dialog.setTitle("Update");
            dialog.setMessage("Updated Your Menu");
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

        EditText categoryName, cPrice;
        ImageView img;
        Button btnUpdateMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.tvMenuName);
            cPrice = itemView.findViewById(R.id.tvPrice1);
            btnUpdateMenu = itemView.findViewById(R.id.btnUpdateMenu1);
            img = itemView.findViewById(R.id.img1);

        }
    }
}
