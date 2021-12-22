package com.example.authentication;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.cardview.widget.CardView;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    TextView welcome;
    String userName = "";
    String splitEmail[];
    Button btnLogOut;
    FirebaseAuth mAuth;
    String header;


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


    public void cardActivity(@NonNull View v){

        TextView name = (TextView) findViewById(v.getId()) ;
        Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
        myIntent.putExtra("name",name.getText().toString());
        startActivity(myIntent);


       /* else if(v.getId() == R.id.art){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name",v.getId());
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.food){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name",v.getId());
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.game){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name",v.getId());
            startActivity(myIntent);
        }
        else if(v.getId() == R.id.chat){
            Intent myIntent = new Intent(MainActivity.this,CardDetails.class);
            myIntent.putExtra("name",v.getId());
            startActivity(myIntent);
        }*/
    }
}

