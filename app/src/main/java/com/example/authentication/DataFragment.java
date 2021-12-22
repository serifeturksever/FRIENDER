package com.example.authentication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.authentication.databinding.FragmentDataBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    private FragmentDataBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<DataModel> dataHolder;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDataBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inflate the layout for this fragment
        // View view = inflater.inflate(R.layout.fragment_data, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<>();

        DataModel obj1=new DataModel(R.drawable.angular,"Angular","Web Application");
        dataHolder.add(obj1);

        DataModel obj2=new DataModel(R.drawable.cp,"C Programming","Embed Programming");
        dataHolder.add(obj2);

        DataModel obj3=new DataModel(R.drawable.cpp,"C++ Programming","Embed Programming");
        dataHolder.add(obj3);

        DataModel obj4=new DataModel(R.drawable.dotnet,".NET Programming","Desktop and Web Programming");
        dataHolder.add(obj4);

        DataModel obj5=new DataModel(R.drawable.java,"Java Programming","Desktop and Web Programming");
        dataHolder.add(obj5);

        DataModel obj6=new DataModel(R.drawable.magento,"Magento","Web Application Framework");
        dataHolder.add(obj6);

        DataModel obj7=new DataModel(R.drawable.nodejs,"NodeJS","Web Application Framework");
        dataHolder.add(obj7);

        DataModel obj8=new DataModel(R.drawable.python,"Python","Desktop and Web Programming");
        dataHolder.add(obj8);

        DataModel obj9=new DataModel(R.drawable.shopify,"Shopify","E-Commerce Framework");
        dataHolder.add(obj9);

        DataModel obj10=new DataModel(R.drawable.wordpress,"Wordpress","WebApplication Framewrok");
        dataHolder.add(obj10);

        recyclerView.setAdapter(new MyAdapter(dataHolder));

        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}