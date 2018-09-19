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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;

public class listitemdesc extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ArrayList<String> Url=new ArrayList<>();
    TextView tv;
    ImageView listDisplay;
    //StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_listitemdesc);
        tv=(TextView)findViewById(R.id.textView);
        listDisplay=(ImageView)findViewById(R.id.listdisplay);
        Intent i=getIntent();
        tv.setText("List of "+i.getStringExtra("key"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final String skey=getIntent().getExtras().getString("ShopKey");
        final String ckey=getIntent().getExtras().getString("customerKey");
       // Log.d("TheShop","^^^^^^"+skey);
        //String po=getIntent().getExtras().getString("itemPositon");
        //Log.d("ThePosition","^^^^^^"+po);
//final int position= Integer.parseInt(getIntent().getExtras().getString("itemPostion"));

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Orders");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map<String,String>> map= (Map<String, Map<String, String>>) dataSnapshot.getValue();
                System.out.println("$$$$$$$$$$"+skey);
                for(Map.Entry m:map.entrySet()){
                    System.out.println("###############"+m.getKey()+" "+m.getValue());
                    if(m.getKey().toString().contains(skey)) {
                        Map<String, String> map2 = (Map<String, String>) m.getValue();
                        if(map2.get("Customer Key").equals(ckey))
                        {
                            Log.d("zzzzzzzzzzz",ckey);

                        String url = map2.get("Image Uri");
                        Log.d("TheURL", "!!!!!" + url);
                        //  Url.add(url);

                        Glide.with(listitemdesc.this)
                                .load(url)
                                .into(listDisplay);
                    }}

                }
               // String u=Url.get(position);

              //  Glide.with(listitemdesc.this)
                //        .load(u)
                  //      .into(listDisplay);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

     /*StorageReference storageRef = FirebaseStorage.getInstance().getReference();
         String i2="https://firebasestorage.googleapis.com/v0/b/bagdelivery-38cd3.appspot.com/o/images%2F-L866h4cKazYXmscnIWL%2F-L867avtkk_YNysZGbgy.jpg?alt=media&token=5ebdd8eb-aaa0-44ed-bd52-28ca630bd40e";
        //DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").child("s2_Wed Mar 21 10:50:00 GMT+05:30 2018").child("")
        StorageReference pathReference = storageRef.child("images/+"+skey);
        Glide.with(this)
                .load(i2)
                .into(listDisplay);//pathReference
        */
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
        getMenuInflater().inflate(R.menu.listitemdesc, menu);
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

        if (id == R.id.logout) {
            // display pending list
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void done(View v){
        Toast.makeText(getApplicationContext(),"Thanks please call the customer",Toast.LENGTH_LONG).show();
       // Intent in = new Intent(listitemdesc.this, shoplist.class);
        //in.putExtra("key",key);
        //startActivity(in);
    }
}
