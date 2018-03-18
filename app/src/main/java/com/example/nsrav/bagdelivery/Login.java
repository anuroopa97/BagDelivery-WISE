package com.example.nsrav.bagdelivery;

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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String my = "data";
    public static final String Password = "passKey";
    public static final String Phone = "phoneKey";
    EditText ed1,ed2;
    Button e;
    TextView t;
    String email,password,uemail,upass;
    SharedPreferences s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        s = getSharedPreferences("data", Context.MODE_PRIVATE);
        if(s.contains("Password")&&(s.contains("Phone"))) {
            ed2.setText(s.getString(Password, ""));
            ed1.setText(s.getString(Phone, ""));
        }
        else
            Toast.makeText(getApplicationContext(),"Create an account",Toast.LENGTH_LONG).show();
        t=(TextView) findViewById(R.id.textView2);
        e=(Button)findViewById(R.id.login);
        String s="Frist time!Create Account";
        SpannableString content=new SpannableString(s);
        content.setSpan(new UnderlineSpan(),0,s.length(),0);
        t.setText(content);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Login.this,SignUpScreen.class);
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
        getMenuInflater().inflate(R.menu.login, menu);
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
    public void enter(View v) {
        if (!(ed1.getText().toString().isEmpty())&& !(ed2.getText().toString().isEmpty())) {


            if ((ed1.getText().toString().equals(s.getString("Phone",""))) && (ed2.getText().toString().equals(s.getString("Password",""))) ){
                //Toast.makeText(getApplicationContext(),"Enter credentials or create account",Toast.LENGTH_LONG).show();
               Log.d("start of login","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

//               String shopposition=getIntent().getExtras().getString("shopposition");
                String customerkey=getIntent().getExtras().getString("customerkey");
                Intent in = new Intent(Login.this, ExCamera.class);
                in.putExtra("customerkey",customerkey);
               // in.putExtra("shopposition",shopposition);


//                Log.d("key",customerkey);
                startActivity(in);
            }
        }
        else
            Toast.makeText(getApplicationContext(),"Enter credentials or create account",Toast.LENGTH_LONG).show();
    }}

