package com.example.authentication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<DataModel> dataHolder;
    private ItemClickListener clickListener; // we will click the room name and go to the chat page so we need this variable

    public MyAdapter(ArrayList<DataModel> dataHolder,ItemClickListener clickListener) { // get dataHolder and clickListener
        this.dataHolder = dataHolder;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // when create room , single_row_design.xml will be called
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) { // we just need room name as header here, so set header only
        holder.header.setText(dataHolder.get(position).getHeader());
        holder.date.setText(dataHolder.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(dataHolder.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder { // get room informations
        TextView header,date;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.emailText);
            date = itemView.findViewById(R.id.roomDate);
        }
    }

    public interface ItemClickListener { // clickLlistener interface
        public void onItemClick(DataModel dataModel);
    }

}