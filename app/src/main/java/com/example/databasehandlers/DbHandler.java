package com.example.databasehandlers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.helpers.Registration;
import com.example.referbussinessapp.MainActivity;
import com.example.referbussinessapp.RegisterActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class DbHandler {

    private DatabaseReference myDBReference;

    public DatabaseReference getInstanceOfPendingStudents(){
        myDBReference = FirebaseDatabase.getInstance().getReference().child("pendinglist");
        
        return myDBReference;
    }

    public void writeIntoPendingList(Context context, Registration registerStudentData){
        
        DatabaseReference dbRef = getInstanceOfPendingStudents();
        dbRef.push().setValue(registerStudentData);

        Toast.makeText(context, "Data Entered Successful!  ", Toast.LENGTH_LONG).show();




    }




}
