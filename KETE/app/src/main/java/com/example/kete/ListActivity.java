package com.example.kete;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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

import androidx.appcompat.app.AppCompatActivity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "QRGeneration";

    private Button add_list_button;
    private Button edit_list_button;
    private EditText emailid;

    ImageView imageCode;
    FirebaseDatabase fDatabase;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    DatabaseReference dRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        Button btnGenerate = findViewById(R.id.qr_button);
//
//        emailid = findViewById(R.id.enter_email);
//        //Text will be entered here to generate QR code
//        //EditText etText = findViewById(R.id.etText);
//        ImageView imageCode = findViewById(R.id.qr_code_image);     //to be checked
//
//        btnGenerate.setOnClickListener(v -> {
//                    //getting text from input text field.
//                     String myText = emailid.getText().toString().trim();
//                    //initializing MultiFormatWriter for QR code
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
////                        savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath() + "/QRCode/";
////                    }
////                    final Bundle bundle = getIntent().getExtras();
//                    MultiFormatWriter mWriter = new MultiFormatWriter();
//                    try {
//                        //BitMatrix class to encode entered text and set Width & Height
//                        BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400, 400);
//                        BarcodeEncoder mEncoder = new BarcodeEncoder();
//                        Bitmap mBitmap = mEncoder.createBitmap(mMatrix);//creating bitmap of code
//                        imageCode.setImageBitmap(mBitmap);//Setting generated QR code to imageView
//                        // to hide the keyboard
//                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                        //manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
//                    } catch (WriterException e) {
//                        e.printStackTrace();
//                    }
////                    fDatabase = FirebaseDatabase.getInstance();
////                    dRef = fDatabase.getReference().child("email");
////
////                    dRef.child("email").addValueEventListener(new ValueEventListener() {
////                        @Override
////                        public void onDataChange(@NonNull DataSnapshot snapshot) {
////                            if (snapshot.exists()) {
////                                e_mail = snapshot.getValue(String.class);
////                                Toast.makeText(ListActivity.this, e_mail, Toast.LENGTH_SHORT).show();
////                            } else {
////                                Toast.makeText(ListActivity.this, "You may not registered before!", Toast.LENGTH_SHORT).show();
////                            }
////                        }
////
////                        @Override
////                        public void onCancelled(@NonNull DatabaseError error) {
////
////                        }
////                    });
//        });
        findViewById(R.id.add_list_button).setOnClickListener(v -> {
            startActivity(new Intent(ListActivity.this, AddListActivity.class));
        });
        findViewById(R.id.edit_list_button).setOnClickListener(v -> {
            startActivity(new Intent(ListActivity.this, EditListActivity.class));
        });
    }
}
