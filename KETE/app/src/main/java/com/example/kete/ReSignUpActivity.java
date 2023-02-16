package com.example.kete;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReSignUpActivity extends AppCompatActivity {

    DatabaseReference dRef;
    FirebaseDatabase fDatabase;

    private FirebaseAuth auth;
    private EditText name, shop_name;
    private EditText signup_Email, signup_Password;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_sign_up);


        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        shop_name = findViewById(R.id.shop_name);
        signup_Email = findViewById(R.id.re_signup_email);
        signup_Password = findViewById(R.id.re_signup_password);
        signupButton = findViewById(R.id.re_signup_button);
        loginRedirectText = findViewById(R.id.re_loginRedirectText);

        fDatabase = FirebaseDatabase.getInstance();
        dRef = fDatabase.getReference().child("");

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String rename = name.getText().toString();
                String sname = shop_name.getText().toString();
                String user = signup_Email.getText().toString().trim();
                String pass = signup_Password.getText().toString().trim();
                dRef.child("email").setValue(user);
//                dRef.child("retailer name").setValue(rename);
//                dRef.child("shop name").setValue(sname);

                if (user.isEmpty()){
                    signup_Email.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    signup_Password.setError("Password cannot be empty");
                } else{
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ReSignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ReSignUpActivity.this, ReLoginActivity.class));
                            } else {
                                Toast.makeText(ReSignUpActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }

        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReSignUpActivity.this, ReLoginActivity.class));
            }
        });
    }
//    public void sendData(View view){
//        writeNewUser();
//    }
//    public void writeNewUser(){
//        Retailer retailer = new Retailer(name.getText().toString(), shop_name.getText().toString(), signup_Email.getText().toString());
//
//        database.child("retailer").child(retailer.getName()).setValue(retailer);
//    }
}