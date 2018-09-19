package com.hari.nsrav.bagdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Hshopslist extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> shopdesc=new ArrayList<>();
    ArrayList<String> address=new ArrayList<>();
    ArrayList<String> phone=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> ShopKey=new ArrayList<>();

    String Shopdesc,ShopKe,il;
    String add,nam,phon;

    ListView listView;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hshopslist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listView = (ListView) findViewById(R.id.listView);
       // Intent x=getIntent();
         //y=x.getExtras().getString("Shoptype");
         il=getIntent().getExtras().getString("Shoptype");
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);

       // db = FirebaseDatabase.getInstance().getReference(il);



        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Shopkeeper");
        ref.child(il).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map<String,String>> map= (Map<String, Map<String, String>>) dataSnapshot.getValue();
                for(Map.Entry m:map.entrySet()){
                    System.out.println(m.getKey()+" "+m.getValue());

                   // ShopKe=String.valueOf(m.getKey());
                    ShopKey.add(String.valueOf(m.getKey()));

                    Map<String,String> map2= (Map<String, String>) m.getValue();

                    // Log.d("phoneValue","***********************************"+phone);
                    add=map2.get("address").toString();
                      nam = map2.get("name").toString();
                   phon=String.valueOf(map2.get("phone"));
                   address.add(add);
                   name.add(nam);
                   phone.add(phon);
                    //shopdesc.add(phone);
                    //shopdesc.add(address);
                    //shopdesc.add(name);
                    //shopdesc.add(ShopKey);
                    Log.d("shopdesc","$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+shopdesc);
                    Shopdesc=name+"\n"+address+"\n"+phone+"\n"+ShopKey;
                   // adapter.add(name+"\n"+address+"\n"+phone);
                    adapter.add(nam);
                  //  adapter.add(address);

                    //adapter.add(phone);


                    listView.setAdapter(adapter);

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


       // String[] items = {"letter for attendance", "Rigolade", "Internships", "WISE"};
       /// ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                Intent i = new Intent(Hshopslist.this, Shoplist2.class);
              //  DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Shopkeeper").child(il);
              // i.putExtra("Shopdesc", Shopdesc);
              // i.putExtra("shopposition",position);
                String sname=name.get(position);
                String saddress=address.get(position);
                String sphone=phone.get(position);
                String skey=ShopKey.get(position);
                i.putExtra("name",sname);
                i.putExtra("address",saddress);
                i.putExtra("phone",sphone);
                i.putExtra("key",skey);

                startActivity(i);

            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hshopslist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myorders) {
            // Handle the camera action
        } else if (id == R.id.status) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

