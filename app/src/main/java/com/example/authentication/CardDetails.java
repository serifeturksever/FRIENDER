package com.example.authentication;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class CardDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card); // use activity_card.xml


        // Create detail fragment
        Bundle bundle = new Bundle();
        bundle.putString("odaIsmi",getIntent().getExtras().getString("name")); // pass some arguments to use in datafragment
        bundle.putString("email",getIntent().getExtras().getString("email"));
        DataFragment dataFragment = new DataFragment();
        dataFragment.setArguments(bundle);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,dataFragment).commit(); // use xml file that have maincontainer id
    }



}