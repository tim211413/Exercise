package com.example.add_user_mvvm.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<User> userArrayList;

    public MyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_user_name, tv_user_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_user_phone = itemView.findViewById(R.id.tv_user_phone);
        }
    }

    @NonNull
    @NotNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.ViewHolder holder, int position) {
        holder.tv_user_name.setText(userArrayList.get(position).getUserName());
        holder.tv_user_phone.setText(userArrayList.get(position).getUserPhone());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}
