package com.example.kete;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrScannerActivity extends AppCompatActivity implements View.OnClickListener {
    Button qr_scan;
    String textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        qr_scan = findViewById(R.id.qr_scan);
        // adding listener to the button
        qr_scan.setOnClickListener(this);
    }
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("SCAN");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
            else{
                textView = intentResult.getContents().toString();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}