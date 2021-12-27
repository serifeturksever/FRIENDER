package com.example.authentication;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment implements MyAdapter.ItemClickListener{

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("message");



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<DataModel> dataHolder;
    ArrayAdapter<String> arrayAdapter;

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
        Log.d("message","test 1");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("odaIsmi");
            //mParam2 = getArguments().getString(ARG_PARAM2);
            Log.d("message",mParam1);
            //Log.d("message",mParam2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("message","test 2");

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().getRoot();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataHolder.clear();
                for (DataSnapshot roomDataSnap : dataSnapshot.getChildren()){
                    for(DataSnapshot rmchild: roomDataSnap.getChildren()){
                        for(DataSnapshot subrmchild: rmchild.getChildren()){
                            Log.d("room_name",subrmchild.getValue().toString());
                            DataModel data_model = new DataModel(subrmchild.getValue().toString());
                            dataHolder.add(data_model);
                        }
                    }
                }
                MyAdapter myAdapter = new MyAdapter(dataHolder,DataFragment.this::onItemClick);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("message","firebase error");
            }
        });

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_card, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();

        /*DataModel obj1=new DataModel(R.drawable.java,"Java Programming","Desktop and Web Programming");
        dataHolder.add(obj1);

        DataModel obj2=new DataModel(R.drawable.nodejs,"NodeJS","Web Application Framework");
        dataHolder.add(obj2);*/


        MyAdapter adapter = new MyAdapter(dataHolder, (MyAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);

        Button sendButton = view.findViewById(R.id.createRoom);
        EditText roomName = (EditText) view.findViewById(R.id.roomName);
        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("rooms");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataModel obj3=new DataModel(roomName.getText().toString());
                dataHolder.add(obj3);
                recyclerView.setAdapter(adapter);
                reference1.push().setValue(roomName.getText().toString());
                roomName.setText("");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onItemClick(DataModel dataModel){
        Fragment fragment = DetailFragment.newInstance(dataModel.getHeader()); // DetailFragment.newInstance(dataModel.getHeader());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer,fragment,"detail_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}