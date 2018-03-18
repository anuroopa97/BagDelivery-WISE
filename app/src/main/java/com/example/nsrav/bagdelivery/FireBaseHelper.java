package com.example.nsrav.bagdelivery;

/**
 * Created by nsrav on 17-03-2018.
 */

import android.util.Log;

import com.google.firebase.database.ChildEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FireBaseHelper {

        DatabaseReference db;
        //Boolean saved=null;
        ArrayList<String> spacecrafts=new ArrayList<>();

        public FireBaseHelper(DatabaseReference db) {
            this.db = db;
        }

        //WRITE

        //READ
        public ArrayList<String> retrieve()
        {
            db.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    fetchData(dataSnapshot);
                    ArrayList<String> l=fetchData(dataSnapshot);
                    Log.d("return values","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+l.get(0));

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return spacecrafts;
        }

        private ArrayList<String> fetchData(DataSnapshot dataSnapshot)
        {
            spacecrafts.clear();

            for (DataSnapshot ds : dataSnapshot.getChildren())
            {
              //  String name=ds.getValue(Users.class).getName();
               // spacecrafts.add(name);
            }
            return spacecrafts;
        }
    }

