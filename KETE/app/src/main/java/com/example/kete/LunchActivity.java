package com.example.kete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LunchActivity extends AppCompatActivity {
    private Button retailer_button;
    private Button customer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        retailer_button = findViewById(R.id.retailer_button);
        customer_button = findViewById(R.id.customer_button);

        retailer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LunchActivity.this, ReSignUpActivity.class));
            }
        });

        customer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LunchActivity.this, SignUpActivity.class));
            }
        });
    }
}