package com.example.authentication;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
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
            mParam1 = getArguments().getString("odaIsmi"); // odaIsmi => example: sport
            mParam2 = getArguments().getString("email"); // current user email example: serife@serife.com
            mParam3 = getArguments().getString("chatRoomName"); // clicked room name example: oda 1
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        DatabaseReference reference2;
        reference2 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("chats");

        // we seperate room and chat child in database.So we retrieve chat data from child "chats" part.
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageHolder.clear(); // all messages will be retrieved every time so we have to clear messageHolder at the start of the function
                for(DataSnapshot rmchild: dataSnapshot.getChildren()) {
                    if(rmchild.getKey().equals(mParam3)){ // retrieve all messages that this room have
                        for (DataSnapshot subsubrmchild : rmchild.getChildren()) {

                            //create message object with email,message and date
                            MessageModel message_model = new MessageModel(subsubrmchild.child("email").getValue().toString(), subsubrmchild.child("message").getValue().toString(), subsubrmchild.child("date").getValue().toString());
                            messageHolder.add(message_model);
                        }
                    }
                }
                MessageAdapter messageAdapter = new MessageAdapter(messageHolder);
                chatRecview.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("message","firebase error");

            }


        });
        chatRecview = view.findViewById(R.id.chatRecview); // get recyclerView
        chatRecview.setLayoutManager(new LinearLayoutManager(getContext()));
        messageHolder = new ArrayList<>();

        MessageAdapter adapter = new MessageAdapter(messageHolder);
        chatRecview.setAdapter(adapter);

        Button sendButton = view.findViewById(R.id.chatSendButton);
        EditText chatMessageText = view.findViewById(R.id.chatMessageText);

        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("chats").child(mParam3); // call clicked room
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateCreator dateCreator = new DateCreator();

                if(chatMessageText.getText().toString().length() > 0){
                    MessageModel message = new MessageModel(mParam2,chatMessageText.getText().toString(),dateCreator.getCurrentHourAndMinute());
                    messageHolder.add(message);
                    chatRecview.setAdapter(adapter);

                    Map<String, String> messageData = new HashMap<>(); // put email,message and date into hashmap and add to firebase with this way.
                    messageData.put("email",mParam2);
                    messageData.put("message",chatMessageText.getText().toString());
                    messageData.put("date",dateCreator.getCurrentHourAndMinute());

                    reference1.push().setValue(messageData); // add message to the firebase realtime database
                    chatMessageText.setText("");
                } else {
                    Toast toast = Toast.makeText(getActivity(),"Length of message must be at least 1 character!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0); // Finally, show the toast toast.show();
                    toast.show();
                }

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // app bar settings
        ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.logout)));
        actionBar.setTitle(mParam1.toUpperCase() + " - " + mParam3); // set app bar title
        actionBar.show();
        ((AppCompatActivity)getActivity()).getSupportActionBar();

        super.onViewCreated(view, savedInstanceState);
    }
}