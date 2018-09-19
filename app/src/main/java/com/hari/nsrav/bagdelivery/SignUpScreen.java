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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference mDatabase;
    private DatabaseReference mUserReference;
    String uname,usphone,uaddress;
    String ckey;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
         final EditText name=(EditText)findViewById(R.id.name);
         final EditText uphone=(EditText)findViewById(R.id.phonenumber);
         final EditText pass=(EditText)findViewById(R.id.password);
         final EditText address=(EditText)findViewById(R.id.address);
        Button submit=(Button)findViewById(R.id.submit1);



        //mDatabase.child("Users").updateChildren(users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final String shopkey=getIntent().getExtras().getString("shopkey");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checking","@@@@@@@@@@@##############################################");

              /*  SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);

                String newUEmail=uphone.getText().toString();
                String newPass=pass.getText().toString();
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("Phone",newUEmail);
                editor.putString("Password",newPass );
                editor.commit();*/
                Log.d("check","####################################");

                usphone=uphone.getText().toString();
                uname=name.getText().toString();
                uaddress=address.getText().toString();

              Log.d("values","@@@@@@@@@@@@@@@@@@@@@@@@@"+uname);
              if(usphone.length()<10&&!isValidPhone(usphone))
              {
                  Toast.makeText(getApplicationContext(),"invalid phone number.Try another",Toast.LENGTH_LONG).show();
                  Log.d("a","********************************************");
              }
                else {
              if(uname.length()<2&&!isValidName(uname))
                  {
                      Toast.makeText(getApplicationContext(),"invalid name.Try Another",Toast.LENGTH_LONG).show();

                  }
                  else {
                      if(pass.getText().toString().length()<6&&!isValidpswd(pass.getText().toString()))
                      {
                          Toast.makeText(getApplicationContext(),"Your password should contain atleast one character and one number.Try again",Toast.LENGTH_LONG).show();

                          // Toast.makeText(getApplicationContext(),"Invalid password.Your password must contain atleast a character and a number").show();
                      }
                      else {
                          mDatabase = FirebaseDatabase.getInstance().getReference();
                          //mUserReference=FirebaseDatabase.getInstance().getReference("Users");
                          ckey = mDatabase.child("Users").push().getKey();
                          Log.d("tag1", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + uname);
                          Log.d("tag2", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + usphone);
                          Log.d("tag3", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + uaddress);

                          Users users = new Users(uname, usphone, uaddress, "pending");
                          Map<String, Object> postValues = users.toMap();
                          Map<String, Object> childUpdates = new HashMap<>();
                          childUpdates.put("/Users/" + ckey, postValues);
                          // childUpdates.put("/user/" + userId + "/" + key, postValues);
                          mDatabase.updateChildren(childUpdates);
                          preferences = getSharedPreferences("data", MODE_PRIVATE);

                          String newUEmail = uphone.getText().toString();
                          String newPass = pass.getText().toString();
                          SharedPreferences.Editor editor = preferences.edit();
                          editor.putString("Phone", newUEmail);
                          editor.putString("Password", newPass);
                          editor.putString("customerkey", ckey);
                          editor.commit();
                          Intent i = new Intent(SignUpScreen.this, Login.class);
                          Log.d("shopkeysignup", "@@@@@@@;@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + shopkey);
                          Log.d("custkeysignup", "@@@@@@@;@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ckey);

                          i.putExtra("customerkey", ckey);
                          i.putExtra("shopkey", shopkey);
                          i.putExtra("name",uname);
                          startActivity(i);
                      }

                  }
              }


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
        getMenuInflater().inflate(R.menu.sign_up_screen, menu);
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
            SharedPreferences.Editor editor=preferences.edit();
            editor.remove("Phone");
            editor.remove("Password");
            editor.remove("Key");
            editor.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
public static boolean isValidPhone(final String phone)
{
    Pattern pattern;
    Matcher matcher;
    final String Phone_Pattern="^[1-9][0-9]{9}";
    pattern=Pattern.compile(Phone_Pattern);
    matcher=pattern.matcher(phone);
    return matcher.matches();

}
public static boolean isValidName(final String name)
{
    Pattern pattern;
    Matcher matcher;
    final String name_Pattern="[a-z A-Z]*";
    pattern=Pattern.compile(name_Pattern);
    matcher=pattern.matcher(name);
    return matcher.matches();

}
public static boolean isValidpswd(final String psd)
{
    Pattern pattern;
    Matcher matcher;
    final String psd_pattern="^([a-zA-z0-9]+)";
    pattern=Pattern.compile(psd_pattern);
    matcher=pattern.matcher(psd);
    return matcher.matches();
}
}
