package com.hari.nsrav.bagdelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Orderslist extends AppCompatActivity {
TextView tv;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderslist);
        tv=(TextView)findViewById(R.id.shop);
        Intent i=getIntent();
        String y=i.getExtras().getString("ShopKey");
        String y1=i.getExtras().getString("custKey");
        String y2=i.getExtras().getString("image");
        tv.setText(y+y1+y2);
        b1=(Button)findViewById(R.id.finished);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderslist.this,shoplist.class);
                startActivity(intent);

            }
        });

    }
}
