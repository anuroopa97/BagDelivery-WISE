package com.hari.nsrav.bagdelivery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExCamera extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    Intent intent;
    //static String key;
    Uri downloadUrl;
ImageView mimageView;
    public String customerkey,orderKey,cname;
    public String shopkey;
    DatabaseReference mDatabase;
    StorageReference mStoragePathRef;
    CheckBox checkb;
    Button bt;
    String key;
String image1="a";
   // public String key=  intent.getStringExtra("key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_camera);
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        intent = getIntent();
        Button button = (Button) this.findViewById(R.id.tv);
        //TextView tv=(TextView)this.findViewById(R.id.tv1);
        customerkey=getIntent().getExtras().getString("customerkey");
        cname=getIntent().getExtras().getString("name");
        shopkey=getIntent().getExtras().getString("shopkey");
        Log.d("shop position","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+shopkey);
        Log.d("customer position","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+customerkey);
        checkb=(CheckBox)findViewById(R.id.delivery);
        if(checkb.isChecked())
          key="true";
        else
            key="false";
       // String message = intent.getStringExtra("key");
        //FirebaseStorage storage = FirebaseStorage.getInstance();
       // String customerkey=getIntent().getExtras().toString("customerkey");
        bt=(Button)findViewById(R.id.done);
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (image1.equals("a")) {
                    // Log.d("image","image"+image1);
                    Toast.makeText(getApplicationContext(), "Click a picture of the list to send to the shopkeeper", Toast.LENGTH_LONG).show();
                }
                else{
                Intent intent = new Intent(ExCamera.this,EndingActivity.class);
               // intent.putExtra("Shoptype","Medical");//Testing.class
                // Log.d("Sravya","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                startActivity(intent);
            }}
        });

        }


    public void takeImageFromCamera(View view) {

            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");

                mimageView.setImageBitmap(mphoto);

                String str = encodeTobase64(mphoto);
                image1=str;

           // Toast.makeText(this, "code=== "+str, Toast.LENGTH_SHORT).show();
           /* DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("orders");//push().child("ImageUrl")
            key=ref.getKey();

            ref.setValue(str);

             ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child(key)
                    .child("customerKey");
            ref.setValue(customerkey);
          ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child("order1")
                    .child("delivery");
            ref.setValue(key);

            ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child(key)
                    .child("shopPosition");
ref.setValue(shopposition);
*/
        }
    }
    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();

        //checkbox part:


// Create a reference to 'images/mountains.jpg'


        StorageReference mstorageRef = FirebaseStorage.getInstance().getReference();


         mStoragePathRef = mstorageRef.child("images/"+shopkey+"/"+customerkey+".jpg");
       // StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("orders").child(key);
        UploadTask uploadTask = mStoragePathRef.putBytes(b);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL
                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();

               downloadUrl = taskSnapshot.getDownloadUrl();
               Orders.imageB=taskSnapshot.getDownloadUrl().toString();
                Orders.key=key;
                Orders.customerkey=customerkey;
                Orders.shopposition=shopkey;
                Date date = new Date();

                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Orders").child(shopkey+"_"+date.toString());
                ref.child("Shop Key").setValue(Orders.shopposition);
                ref.child("Customer Key").setValue(Orders.customerkey);
                ref.child("Delivery Key").setValue(Orders.key);
                ref.child("Image Uri").setValue(Orders.imageB);
                ref.child("Name").setValue(cname);

            }
        });
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);


      mDatabase = FirebaseDatabase.getInstance().getReference();
        //mUserReference=FirebaseDatabase.getInstance().getReference("Users");
        orderKey=mDatabase.child("orders").push().getKey();
       // orderKey=mDatabase.push().getKey();
        Log.d("ordercustomer","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+customerkey);
        Log.d("ordershop","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+shopkey);
        Log.d("downloadUrl","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+mStoragePathRef);//mStoragePathRef

       //Orders users=new Orders(key,customerkey,shopkey,mStoragePathRef);

        return imageEncoded;


          }



    }


