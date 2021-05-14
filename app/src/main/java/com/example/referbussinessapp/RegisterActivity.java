package com.example.referbussinessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databasehandlers.DbHandler;
import com.example.helpers.Registration;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText nameEditText;
    TextInputEditText usernameEditText;
    TextInputEditText emailEditText;
    TextInputEditText passwordEditText;

    DbHandler dbHandler = new DbHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void doRegister(View view){

        nameEditText = (TextInputEditText) findViewById(R.id.nameEditText);
        usernameEditText = (TextInputEditText) findViewById(R.id.usernameEditText);
        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordEditText);


        Registration registerStudentData = new Registration(
                nameEditText.getText().toString(),
                usernameEditText.getText().toString(),
                emailEditText.getText().toString(),
                passwordEditText.getText().toString());




        dbHandler.writeIntoPendingList(getApplicationContext(),registerStudentData);


        startActivity(new Intent(RegisterActivity.this, MainActivity.class));

    }
}