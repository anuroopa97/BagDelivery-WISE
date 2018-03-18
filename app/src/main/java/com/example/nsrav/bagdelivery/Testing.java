package com.example.nsrav.bagdelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Testing extends AppCompatActivity {
    ListView lv;
    ArrayList<String> list=new ArrayList<>();
    DatabaseReference db;
    ListView listView;
    //  FireBaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        listView=(ListView)findViewById(R.id.lv);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listView.setAdapter(adapter);
        db = FirebaseDatabase.getInstance().getReference("Users");



        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Shopkeeper").child("Grocerries");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map<String,String>> map= (Map<String, Map<String, String>>) dataSnapshot.getValue();
                for(Map.Entry m:map.entrySet()){
                    System.out.println(m.getKey()+" "+m.getValue());
                   /* Map<String,String> map2= (Map<String, String>) m.getValue();
                    for(Map.Entry m1:map.entrySet()){
                        System.out.println(m1.getKey()+" "+m1.getValue());

                    }*/
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



        /*ChildEventListener childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users users = dataSnapshot.getValue(Users.class);

                Log.d("returned", "@@@@@@@@@@@@@@@@@@@@@@@@" + users.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Users users = dataSnapshot.getValue(Users.class);
                Log.d("returned", "@@@@@@@@@@@@@@@@@@@@@@@@" + users.toString());
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

            //helper=new FireBaseHelper(db);
        db.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }    );
// ArrayList<String> h=helper.retrieve();
// Log.d("Returned valu","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +h.get(0));

        //ADAPTER
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Testing.this, helper.retrieve().get(position), Toast.LENGTH_SHORT).show();
            }
        });*/

}


