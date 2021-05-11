package com.example.databasehandlers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbHandler {

    private DatabaseReference myDBReference;

    public void createInstance(){
        myDBReference = FirebaseDatabase.getInstance().getReference();
    }

}
