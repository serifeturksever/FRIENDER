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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<DataModel> dataHolder;
    private ItemClickListener clickListener;

    public MyAdapter(ArrayList<DataModel> dataHolder,ItemClickListener clickListener) {
        this.dataHolder = dataHolder;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.img.setImageResource(dataHolder.get(position).getImage());
        holder.header.setText(dataHolder.get(position).getHeader());
        //holder.description.setText(dataHolder.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() { // itemView ?
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

    class myViewHolder extends RecyclerView.ViewHolder {
        //ImageView img;
        TextView header; //,description;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //img = itemView.findViewById(R.id.img1);
            header = itemView.findViewById(R.id.t1);
            //description = itemView.findViewById(R.id.t2);
        }
    }

    public interface ItemClickListener {

        public void onItemClick(DataModel dataModel);
    }

}