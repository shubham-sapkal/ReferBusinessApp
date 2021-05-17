package com.example.referbussinessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databasehandlers.DbHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    TextView usernameEditText;
    TextView emailEditText;

    TextView totalRefferedTextView,totalJoinedTextView,totalIncomeTextView;

    DbHandler dbHandler;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        usernameEditText = (TextView) findViewById(R.id.usernameEditText);
        emailEditText = (TextView) findViewById(R.id.emailEditText);
        totalRefferedTextView = (TextView) findViewById(R.id.totalReferredTextView);
        totalJoinedTextView = (TextView) findViewById(R.id.totalJoinedTextView);
        totalIncomeTextView = (TextView) findViewById(R.id.totalIncomeTextView);

        dbHandler = new DbHandler();
        sharedPreferences = getApplicationContext().getSharedPreferences("userdata", Context.MODE_PRIVATE);

        dbHandler.getInstanceOfStudent().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                Log.d("mytag",sharedPreferences.getString("email,",null));


                for(DataSnapshot ds : snapshot.getChildren()){

                    //checkLogin(ds.child("email").getValue().toString(),ds.child("password").getValue().toString());

                    if(sharedPreferences.getString("email",null).equals(ds.child("email").getValue().toString()) ){

                        Log.d("mytag","Data match sucessfully");

                        usernameEditText.setText(ds.child("username").getValue().toString());
                        emailEditText.setText(ds.child("email").getValue().toString());

                    }

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu1 :
                Toast.makeText(this, "reffered clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu2 :
                Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }
}