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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class shoplist extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    ArrayList<String> custKey=new ArrayList<>();
    ArrayList<String> list=new ArrayList<>();
    ArrayList<String> image=new ArrayList<>();
    ArrayList<String> ShopKey=new ArrayList<>();
    ArrayList<String> item=new ArrayList<>();
    String skey,ckey,img,OrderDesc,OrderKey;
    static int pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        listView=(ListView)findViewById(R.id.lv);
       // String[] items={"CUSTOMER 1","CUSTOMER 2","CUSTOMER 3","CUSTOMER 4"};

       /* DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("orders");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) dataSnapshot.getValue();
                for (Map.Entry m : map.entrySet()) {
                    System.out.println(m.getKey() + " " + m.getValue());
                    OrderKey = String.valueOf(m.getKey());
                    ShopKey.add(String.valueOf(m.getKey()));
                    Map<String, String> map2 = (Map<String, String>) m.getValue();
                    ckey = map2.get("customerKey").toString();
                    skey = map2.get("ShopKey").toString();
                    img = map2.get("imageUrl").toString();
                    image.add(img);
                    custKey.add(ckey);
                    OrderDesc = custKey + "\n" + image + "\n" + ShopKey;
                    adapter.add(OrderKey);
                    listView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/






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
        final String shopkeeperKey =getIntent().getExtras().getString("key");
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);

        //  Log.d("shopk$$",shopkeeperKey);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) dataSnapshot.getValue();
                Log.d("map^^^^^^^^^^^^^^^^^^",map.toString());
                for (Map.Entry m : map.entrySet()) {
                    Log.d("shopk$$",shopkeeperKey);
                    Log.d("sss#####",m.getKey().toString());

                    if(String.valueOf(m.getKey()).contains(shopkeeperKey)){
                        Log.d("shopvalue",""+m.getKey().toString().contains(shopkeeperKey));
                        Map<String, String> map2 = (Map<String, String>) m.getValue();
                        Log.d("customer1@@",map2.get("Customer Key"));
                        item.add(map2.get("Customer Key"));
                        Log.d("itemSize",""+item.size());
                        adapter.add(map2.get("Customer Key"));
                        listView.setAdapter(adapter);

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

       // Log.d("1st item############",items[0]);
      //  final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,items);
       // listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                String key =getIntent().getExtras().getString("key");
                pos=position;

                Log.d("PostionInList","((((((("+pos);
Log.d("yyyyy",s);


                Intent i = new Intent(shoplist.this, listitemdesc.class);

                i.putExtra("ShopKey",key);
                i.putExtra("itemPosition",String.valueOf(pos));
i.putExtra("customerKey",s);
                Log.d("PoistionCheck","((("+String.valueOf(pos));
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
        getMenuInflater().inflate(R.menu.shoplist, menu);
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
            // display pending items list
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
