package com.example.add_user_mvvm.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.add_user_mvvm.R;
import com.example.add_user_mvvm.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<User> userArrayList;

    public MyAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        Log.d("TAG5", "getUserArrayList in MyAdapter: " + userArrayList.toString());
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        Log.d("TAG5", "getUserArrayList in setUserArrayList: " + userArrayList.toString());
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
        User user = userArrayList.get(position);

        holder.tv_user_name.setText(user.getUserName());
        Log.d("TAG1", user.getUserName());
        holder.tv_user_phone.setText(user.getUserPhone());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_name) TextView tv_user_name;
        @BindView(R.id.tv_user_phone) TextView tv_user_phone;
//        TextView tv_user_name;
//        TextView tv_user_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            tv_user_name = itemView.findViewById(R.id.tv_user_name);
//            tv_user_phone = itemView.findViewById(R.id.tv_user_phone);
        }
    }

}
