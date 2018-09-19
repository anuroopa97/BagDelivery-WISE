package com.example.nsrav.bagdelivery;

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

public class ExCamera extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    Intent intent;
    static String key;
ImageView mimageView;
    public String customerkey;
    public String shopposition;
   // public String key=  intent.getStringExtra("key");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_camera);
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        intent = getIntent();
        Button button = (Button) this.findViewById(R.id.tv);
        TextView tv=(TextView)this.findViewById(R.id.tv1);
        customerkey=getIntent().getExtras().getString("customerkey");
        shopposition=getIntent().getExtras().getString("shopposition");
        Log.d("shop position","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+shopposition);
        Log.d("customer position","@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+customerkey);
       // String message = intent.getStringExtra("key");
        //FirebaseStorage storage = FirebaseStorage.getInstance();
    }


    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);
            String str=encodeTobase64(mphoto);
           // Toast.makeText(this, "code=== "+str, Toast.LENGTH_SHORT).show();
            DatabaseReference ref = FirebaseDatabase.getInstance()
                    .getReference("orders");//push().child("ImageUrl")
            key=ref.getKey();

            ref.setValue(str);

             ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child(key)
                    .child("customerKey");
            ref.setValue(customerkey);
        /*    ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child("order1")
                    .child("delivery");
            ref.setValue(key);
            */
            ref = FirebaseDatabase.getInstance()
                    .getReference("orders").child(key)
                    .child("shopPosition");
ref.setValue(shopposition);
        }
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("orders").child(key);
        UploadTask uploadTask = mStorageRef.putBytes(b);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        return imageEncoded;
    }

}


