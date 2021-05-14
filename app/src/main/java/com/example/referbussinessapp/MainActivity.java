package com.example.referbussinessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.example.databasehandlers.DbHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    TextInputEditText emailEditText;
    TextInputEditText passwordEditText;

    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DbHandler();

    }

    public void doLogin(View view){

        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordEditText);


        dbHandler.getInstanceOfStudent().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                // loop will check for credentials for all records to match single record
                for(DataSnapshot ds : snapshot.getChildren()){

                    //Log.d("mytag","email : "+ ds.child("email"));
                    //Log.d("mytag","pass : "+ ds.child("password"));

                    // Calling function to checking the login credentials
                    checkLogin(ds.child("email").getValue().toString(),ds.child("password").getValue().toString());
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void checkLogin(String email,String password){

        //Log.d("mytag","email"+emailEditText.getText().toString());
        //Log.d("mytag","password"+passwordEditText.getText().toString());

        if(email.equals(emailEditText.getText().toString()) && password.equals(passwordEditText.getText().toString()) ){
            Toast.makeText(this, "login successful!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Login failed!", Toast.LENGTH_LONG).show();
        }

    }


    // Trasfer activity to RegisterActivity.java
    public void showRegisterActivity(View view){

        startActivity(new Intent(MainActivity.this,RegisterActivity.class));

    }
}