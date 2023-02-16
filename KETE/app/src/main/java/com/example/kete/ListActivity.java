package com.example.kete;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "QRGeneration";
    private Button qr_button;
    private Button add_list_button;
    private Button edit_list_button;

    ImageView imageView;
    FirebaseDatabase fDatabase;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    DatabaseReference dRef;
    String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        qr_button = findViewById(R.id.qr_button);
        imageView = findViewById(R.id.qr_code_image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/QRCode/";
        }

        final Bundle bundle = getIntent().getExtras();

        fDatabase = FirebaseDatabase.getInstance();
        dRef = fDatabase.getReference().child("email");

        QRGEncoder qrgEncoder = new QRGEncoder(null, bundle, QRGContents.Type.CONTACT, 500);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }

        dRef.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    e_mail = snapshot.getValue(String.class);
                }
                else{
                    Toast.makeText(ListActivity.this, "You may not registered before!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        qr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e_mail.length() > 0) {
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = Math.min(width, height);
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(e_mail, null, QRGContents.Type.TEXT, smallerDimension);

                    try {
                        bitmap = qrgEncoder.getBitmap();
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ListActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
//                //initializing MultiFormatWriter for QR code
//                MultiFormatWriter mWriter = new MultiFormatWriter();
//                try {
//                    //BitMatrix class to encode entered text and set Width & Height
//                    BitMatrix mMatrix = mWriter.encode(e_mail, BarcodeFormat.QR_CODE, 400, 400);
//                    BarcodeEncoder mEncoder = new BarcodeEncoder();
//                    Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
//                    imageView.setImageBitmap(mBitmap);//Setting generated QR code to imageView
//                }
//                catch (WriterException e) {
//                    e.printStackTrace();
//                }
            }
        });

        findViewById(R.id.save_qr).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                try {
                    boolean save = new QRGSaver().save(savePath, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    String result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "can't access to the external storage", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add_list_button).setOnClickListener(v -> {
            startActivity(new Intent(ListActivity.this, AddListActivity.class));
        });
        findViewById(R.id.edit_list_button).setOnClickListener(v -> {
            startActivity(new Intent(ListActivity.this, EditListActivity.class));
        });


    }
}