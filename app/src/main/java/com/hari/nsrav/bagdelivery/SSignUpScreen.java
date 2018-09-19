package com.hari.nsrav.bagdelivery;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SSignUpScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    String uname,usphone,uaddress,utype;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssign_up_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText name=(EditText)findViewById(R.id.sname);
        final EditText uphone=(EditText)findViewById(R.id.sphonenumber);
        final EditText pass=(EditText)findViewById(R.id.spassword);
        final EditText address=(EditText)findViewById(R.id.saddress);
        final EditText type=(EditText)findViewById(R.id.scategory);
        Button submit=(Button)findViewById(R.id.ssubmit1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checking","@@@@@@@@@@@##############################################");


                Log.d("check","####################################");

                usphone=uphone.getText().toString();
                uname=name.getText().toString();
                uaddress=address.getText().toString();
                utype=type.getText().toString();
                Log.d("values","@@@@@@@@@@@@@@@@@@@@@@@@@"+uname);

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Shopkeeper").child(utype);
                //mUserReference=FirebaseDatabase.getInstance().getReference("Users");
                key=mDatabase.child(utype).push().getKey();
                Log.d("tag1","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+uname);
                Log.d("tag2","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+usphone);
                Log.d("tag3","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+uaddress);

               Shopkeeper shopkeeper=new Shopkeeper(uname,usphone,uaddress);
                Map<String, Object> postValues = shopkeeper.toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put( key, postValues);
                // childUpdates.put("/user/" + userId + "/" + key, postValues);
                mDatabase.updateChildren(childUpdates);
                SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);

                String newUEmail=uphone.getText().toString();
                String newPass=pass.getText().toString();
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("Phone",newUEmail);
                editor.putString("Password",newPass );
                editor.putString("shopkey",key);
                editor.commit();

                Intent i=new Intent(SSignUpScreen.this,ShopkeeperLogin.class);
                i.putExtra("key",key);
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
        getMenuInflater().inflate(R.menu.ssign_up_screen, menu);
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
            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
