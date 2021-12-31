package com.example.authentication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myViewHolder> {
    ArrayList<MessageModel> messageHolder;

    public MessageAdapter(ArrayList<MessageModel> messageHolder) {
        this.messageHolder = messageHolder;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row_design,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.email.setText(messageHolder.get(position).getEmail());
        holder.message.setText(messageHolder.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return messageHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView message;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.emailText);
            message = itemView.findViewById(R.id.messageText);
        }
    }

}