package com.example.authentication;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment implements MyAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView tv2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<DataModel> dataHolder;
    ArrayAdapter<String> arrayAdapter;
    String roomNameForArgumentPass;


    public DataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment();
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
            mParam1 = getArguments().getString("odaIsmi");
            mParam2 = getArguments().getString("email");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().getRoot(); // call firebase database;

        // mParam2 => userEmail
        String currentUserFirebaseEmail = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser().getEmail();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataHolder.clear(); // all chats will be retrieved every time so we have to clear messageHolder at the start of the function
                for(DataSnapshot rmchild: dataSnapshot.child(mParam1).child("rooms").getChildren()) {
                        DataModel data_model = new DataModel(rmchild.child("roomName").getValue().toString(),rmchild.child("date").getValue().toString(),rmchild.child("categoryName").getValue().toString(),rmchild.child("creator").getValue().toString()); //create room object to to list in recyclerView
                        dataHolder.add(data_model);
                }
                MyAdapter myAdapter = new MyAdapter(dataHolder,DataFragment.this::onItemClick,mParam1); // creater room adapder
                recyclerView.setAdapter(myAdapter); // use this adapter in recyclerView
                myAdapter.notifyDataSetChanged(); // in order to my adapter notify changes and apply what progrom need
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("message","firebase error");
            }
        });

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_card, container, false); // use activity_card.xml
        recyclerView = view.findViewById(R.id.recview); // get recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(dataHolder, (MyAdapter.ItemClickListener) this,mParam1);
        recyclerView.setAdapter(adapter);

        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("rooms");
        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder = new AlertDialog.Builder(DataFragment.this.getContext());
                final View createRoom = getLayoutInflater().inflate(R.layout.popup,null);

                dialogBuilder.setView(createRoom);
                dialog = dialogBuilder.create();
                dialog.show();

                Button sendButton = createRoom.findViewById(R.id.createRoom); // button for creating room
                EditText roomName = createRoom.findViewById(R.id.roomName); // input area that holds room name

                TextView roomCreateWarn = createRoom.findViewById(R.id.roomCreateWarn); // created for validation (warn) messages

                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(roomName.getText().length() > 3){ // room name validation
                            DateCreator dateCreator = new DateCreator();

                            DataModel obj3=new DataModel(roomName.getText().toString(),dateCreator.getCurrentFullDate(),mParam1,mParam2); // create new room Object
                            dataHolder.add(obj3);
                            recyclerView.setAdapter(adapter);


                            Map<String, String> roomData = new HashMap<>(); // put email,message and date into hashmap and add to firebase with this way.
                            roomData.put("categoryName",mParam1);
                            roomData.put("roomName",roomName.getText().toString());
                            roomData.put("date",dateCreator.getCurrentFullDate());
                            roomData.put("creator",mParam2);

                            reference1.push().setValue(roomData); // add message to the firebase realtime database
                            //reference1.push().child(roomName.getText().toString()); // add room name to firebase realtime database
                            roomNameForArgumentPass = roomName.getText().toString();
                            roomCreateWarn.setText(""); // clear input area
                            roomName.setText(""); // clear the input area
                            dialog.dismiss();
                        } else {
                            roomCreateWarn.setText("Length of room name must be bigger than 3");
                        }
                    }
                });

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){

        // app bar settings
        ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.logout)));
        actionBar.setTitle(mParam1.toUpperCase());
        actionBar.show();
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        super.onViewCreated(view,savedInstanceState);

    }

    @Override
    public void onItemClick(DataModel dataModel){
        Fragment fragment = DetailFragment.newInstance(dataModel.getHeader(),mParam2); // DetailFragment.newInstance(dataModel.getHeader());
        // for passing other fragment (detail fragment)
        Bundle bundle = new Bundle();


        bundle.putString("odaIsmi",mParam1); // parameters that sent to other fragment (detail fragment)
        bundle.putString("email",mParam2);
        bundle.putString("chatRoomName", dataModel.getHeader());
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer,fragment,"detail_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

