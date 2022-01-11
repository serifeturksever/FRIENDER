package com.example.authentication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    ArrayList<DataModel> dataHolder;
    private ItemClickListener clickListener; // we will click the room name and go to the chat page so we need this variable
    String category;
    View tempView;

    public MyAdapter(ArrayList<DataModel> dataHolder,ItemClickListener clickListener,String category) { // get dataHolder and clickListener
        this.dataHolder = dataHolder;
        this.clickListener = clickListener;
        this.category = category;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // when create room , single_row_design.xml will be called
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
        this.tempView = view;
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) { // we just need room name as header here, so set header only
        holder.header.setText(dataHolder.get(position).getHeader());
        holder.date.setText(dataHolder.get(position).getDate());

        Button btnDelete = this.tempView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRoom(dataHolder.get(position).getCategory(),dataHolder.get(position).getHeader());
                deleteChat(dataHolder.get(position).getCategory(),dataHolder.get(position).getHeader());
                Log.d("clicked",dataHolder.get(position).getHeader());
                Log.d("category",dataHolder.get(position).getCategory());
            }
        });
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

    public void deleteRoom(String categoryName,String roomName){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query roomsQuery = ref.child(categoryName).child("rooms").orderByChild("roomName").equalTo(roomName);

        roomsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "onCancelled", databaseError.toException());
            }
        });
    }
    public void deleteChat(String categoryName,String roomName){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query chatsQuery = ref.child(categoryName).child("chats").orderByKey().equalTo(roomName);


        chatsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Hata", "onCancelled", databaseError.toException());
            }
        });
    }

}