package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    String userName = "";
    String splitEmail[];
    Button btnLogOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        welcome = (TextView) findViewById(R.id.textView);

        btnLogOut.setOnClickListener(view->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            splitEmail = user.getEmail().split("@",0); // welcome mesajını maile göre dinamik yazdırma kısmı
            userName = splitEmail[0];
            welcome.setText("Welcome " + userName + " :)");
        }
        else{
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
}

