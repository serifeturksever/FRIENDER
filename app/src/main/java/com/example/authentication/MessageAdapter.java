package com.example.authentication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.myViewHolder> {
    ArrayList<MessageModel> messageHolder;
    private int view_type_message_sent = 1;
    private int view_type_message_received = 2;
    private Object FirebaseAuth;

    public MessageAdapter(ArrayList<MessageModel> messageHolder) {
        this.messageHolder = messageHolder;

    }

    @Override
    public int getItemViewType(int position) {

        //return super.getItemViewType(position);
        /*Log.d("position value",messageHolder.get(position).getMessage());*/
        String userEmail = messageHolder.get(position).getEmail();
        String currentUserFirebaseEmail = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if(userEmail.equals(currentUserFirebaseEmail)){
            return view_type_message_sent;

        } else {
            return view_type_message_received;
        }


      /* if("burhan@burhan.com" == com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser().getEmail()){
            return view_type_message_sent;
       } else {
           return view_type_message_received;
       }*/
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("view message type",String.valueOf(viewType));

        if(viewType == view_type_message_received){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row_design,parent,false);
            return new myViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.self_message_row,parent,false);
            return new myViewHolder(view);
        }

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