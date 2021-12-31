package com.example.authentication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<MessageModel> messageHolder;
    RecyclerView chatRecview;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1,String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("mparam2 DETAIL FRAGMENT",mParam2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        DatabaseReference reference;
        //reference = FirebaseDatabase.getInstance().getReference().getRoot();
        reference = FirebaseDatabase.getInstance().getReference().getRoot();
        /*
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageHolder.clear();
                //Log.d("firebase", String.valueOf(dataSnapshot.child(mParam1).getChildren()));
                //Log.d("firebase", String.valueOf(dataSnapshot.child(mParam1).getValue()));
                for(DataSnapshot rmchild: dataSnapshot.child(mParam1).getChildren()) {
                    for (DataSnapshot subrmchild : rmchild.getChildren()) {
                        Log.d("room_name", subrmchild.getValue().toString());
                        DataModel data_model = new DataModel(subrmchild.getValue().toString());
                        messageHolder.add(data_model);
                    }
                }
                //dataHolder.clear();
                MessageAdapter messageAdapter = new MessageAdapter(messageHolder);
                chatRecview.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("message","firebase error");
            }
        });*/

        chatRecview = view.findViewById(R.id.chatRecview);
        chatRecview.setLayoutManager(new LinearLayoutManager(getContext()));
        messageHolder = new ArrayList<>();

        MessageAdapter adapter = new MessageAdapter(messageHolder);
        chatRecview.setAdapter(adapter);

        Button sendButton = view.findViewById(R.id.chatSendButton);
        EditText chatMessageText = (EditText) view.findViewById(R.id.chatMessageText);
        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("rooms").child("message");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageModel message = new MessageModel(mParam2,chatMessageText.getText().toString());
                messageHolder.add(message);
                chatRecview.setAdapter(adapter);
                //reference1.push().setValue("burhan@burhan.com");
                //reference1.push().setValue("selam");
                reference1.child("email").setValue(mParam2);
                reference1.child("message").setValue(chatMessageText.getText().toString());
                chatMessageText.setText("");
            }
        });
        return view;
    }
}