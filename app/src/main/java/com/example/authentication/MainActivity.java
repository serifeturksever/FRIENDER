package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {



    TextView welcome,welcomeEmail;
    String userName = "";
    String splitEmail[];
    Button btnLogOut;
    FirebaseAuth mAuth;
    String header;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar =  getSupportActionBar(); // create action button variable
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.delete))); // change appbar color

        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        welcomeEmail = findViewById(R.id.profileName);
        welcome = findViewById(R.id.textView);

        btnLogOut.setOnClickListener(view->{ // call signOut function that provided by firebase mAuth variable
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class)); // routing with intent
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            userEmail = user.getEmail();
            splitEmail = user.getEmail().split("@",0); // dynamic welcome message according to user email
            userName = splitEmail[0];
            welcomeEmail.setText(userEmail);
            welcome.setText("Welcome " + userName);
        }
        else{
            startActivity(new Intent(MainActivity.this,LoginActivity.class)); // if there is no user go to the login page
        }
    }


    public void cardActivity(@NonNull View v){

        // here, we detect clicked category name and routes user to clicked page with intent using cardDetails page
        if(v.getId() == R.id.sport){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","sport");
            myIntent.putExtra("email",userEmail); // we send userEmail ,because we need it on other pages
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.study){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","study");
            myIntent.putExtra("email",userEmail);
            startActivity(myIntent);
        }

        else if(v.getId() == R.id.art){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","art");
            myIntent.putExtra("email",userEmail);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.food){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","food");
            myIntent.putExtra("email",userEmail);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.game){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","game");
            myIntent.putExtra("email",userEmail);
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.chat){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name","chat");
            myIntent.putExtra("email",userEmail);
            startActivity(myIntent);
        }
    }
}

