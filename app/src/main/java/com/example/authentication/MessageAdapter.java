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
    private int view_type_message_sent = 1; // sender view type
    private int view_type_message_received = 2; // receiver view type

    public MessageAdapter(ArrayList<MessageModel> messageHolder) { // constructor => get just message holder
        this.messageHolder = messageHolder;

    }

    @Override
    public int getItemViewType(int position) {
        // here we detect who sent the message
        // we are going to specify messages position in chat page with this information
        String userEmail = messageHolder.get(position).getEmail();
        String currentUserFirebaseEmail = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser().getEmail();

        if(userEmail.equals(currentUserFirebaseEmail)){
            return view_type_message_sent;

        } else {
            return view_type_message_received;
        }
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == view_type_message_received){ // in getItemViewType we make ready this information and use here.If user message sender is you,ypur messages shown right on the chat page else shown left on the chat page
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row_design,parent,false);
            return new myViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.self_message_row,parent,false);
            return new myViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) { // sets message informations
        holder.email.setText(messageHolder.get(position).getEmail());
        holder.message.setText(messageHolder.get(position).getMessage());
        holder.date.setText(messageHolder.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return messageHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView message;
        TextView date;
        public myViewHolder(@NonNull View itemView) { // get message informations
            super(itemView);
            email = itemView.findViewById(R.id.emailText);
            message = itemView.findViewById(R.id.messageText);
            date = itemView.findViewById(R.id.messageDate);
        }
    }

}