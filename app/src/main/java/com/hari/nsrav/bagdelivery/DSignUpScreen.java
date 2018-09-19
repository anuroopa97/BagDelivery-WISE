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

public class DSignUpScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    String uname,usphone,uaddress;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsign_up_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText name=(EditText)findViewById(R.id.dname);
        final EditText uphone=(EditText)findViewById(R.id.dphonenumber);
        final EditText pass=(EditText)findViewById(R.id.dpassword);
        Button submit=(Button)findViewById(R.id.dsubmit1);



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

                SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);

                String newUEmail=uphone.getText().toString();
                String newPass=pass.getText().toString();
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("Phone",newUEmail);
                editor.putString("Password",newPass );
                editor.commit();
                Log.d("check","####################################");

                usphone=uphone.getText().toString();
                uname=name.getText().toString();

                Log.d("values","@@@@@@@@@@@@@@@@@@@@@@@@@"+uname);

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Delivery");
                //mUserReference=FirebaseDatabase.getInstance().getReference("Users");
                key=mDatabase.push().getKey();
                Log.d("tag1","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+uname);
                Log.d("tag2","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+usphone);
                Log.d("tag3","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+uaddress);

                Delivery delivery=new Delivery(uname,usphone);
                Map<String, Object> postValues = delivery.toMap();
                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put( key, postValues);
                // childUpdates.put("/user/" + userId + "/" + key, postValues);
                mDatabase.updateChildren(childUpdates);

                Intent i=new Intent(DSignUpScreen.this,deliverylist.class);
                i.putExtra("ckey",key);
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
        getMenuInflater().inflate(R.menu.dsign_up_screen, menu);
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
