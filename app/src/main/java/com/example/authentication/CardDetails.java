package com.example.authentication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class CardDetails extends AppCompatActivity {


    TextView cardText;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_details);

        cardText = findViewById(R.id.cardText);

        //  mAuth = FirebaseAuth.getInstance();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String name = bundle.getString("name");
            cardText.setText(name);

        }
    }



}