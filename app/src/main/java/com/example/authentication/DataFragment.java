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

import com.google.android.gms.common.internal.Constants;
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
        Log.d("message","test 1");
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
        //reference = FirebaseDatabase.getInstance().getReference().getRoot();
        reference = FirebaseDatabase.getInstance().getReference().getRoot();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataHolder.clear();
                //Log.d("firebase", String.valueOf(dataSnapshot.child(mParam1).getChildren()));
                //Log.d("firebase", String.valueOf(dataSnapshot.child(mParam1).getValue()));
                        for(DataSnapshot rmchild: dataSnapshot.child(mParam1).getChildren()) {
                          //  Log.d("rmchild",rmchild.toString());

                            for (DataSnapshot subrmchild : rmchild.getChildren()) {
                               // Log.d("subrmchild",subrmchild.getValue().toString());
                                     DataModel data_model = new DataModel(subrmchild.getKey());
                                     dataHolder.add(data_model);
                               // for (DataSnapshot subsubchild : subrmchild.getChildren()) {
                                   // Log.d("subsubchild", subsubchild.getKey());
                               //     DataModel data_model = new DataModel(subsubchild.getKey());
                               //     dataHolder.add(data_model);

                            //    }
                            }
                        }
                //dataHolder.clear();
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

        MyAdapter adapter = new MyAdapter(dataHolder, (MyAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);

        Button sendButton = view.findViewById(R.id.createRoom);
        EditText roomName = (EditText) view.findViewById(R.id.roomName);

        //Log.d("roomnamedata",roomName.getText().toString());



        DatabaseReference reference1;
        reference1 = FirebaseDatabase.getInstance().getReference().child(mParam1).child("rooms");

       // Log.d("reference1data",reference1.getKey().toString());



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataModel obj3=new DataModel(roomName.getText().toString());
                dataHolder.add(obj3);
                recyclerView.setAdapter(adapter);
                reference1.push().child(roomName.getText().toString()); //push().setValue(roomName.getText().toString());
                roomNameForArgumentPass = roomName.getText().toString();
                Log.d("message23",roomNameForArgumentPass);



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

        Log.d("onitemclick içi",dataModel.getHeader());

        Fragment fragment = DetailFragment.newInstance(dataModel.getHeader(),mParam2); // DetailFragment.newInstance(dataModel.getHeader());

        Bundle bundle = new Bundle();

        bundle.putString("odaIsmi",mParam1);
        bundle.putString("email",mParam2);
        bundle.putString("chatRoomName", dataModel.getHeader());


        fragment.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer,fragment,"detail_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}