package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.authentication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CardDetails extends AppCompatActivity {


    TextView cardText;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        /*String name;
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1 != null) {
            name = bundle1.getString("name");
            cardText.setText(name);
        }*/

        Log.d("email",getIntent().getExtras().getString("email"));

        Bundle bundle = new Bundle();
        bundle.putString("odaIsmi",getIntent().getExtras().getString("name"));
        bundle.putString("email",getIntent().getExtras().getString("email"));
        DataFragment dataFragment = new DataFragment();
        dataFragment.setArguments(bundle);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,dataFragment).commit();


        //  mAuth = FirebaseAuth.getInstance();



    }



}