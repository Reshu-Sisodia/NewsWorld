package com.newsworld;

import android.app.ProgressDialog;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, number, password;
    Button signUp;
    TextView already;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.SignUp);
        already = findViewById(R.id.already);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Created Account");
        progressDialog.setMessage("SignUp");
        progressDialog.setCancelable(false);
        already.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().length() != 0) {
                    if (password.getText().toString().length() != 0) {
                        if (name.getText().toString().length() != 0) {
                            if (number.getText().toString().length() == 10) {
                                progressDialog.show();
                                Show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Enter your Number", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Enter your Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Set  your Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Show() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Number = number.getText().toString().trim();
                String Password = password.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        User user = new User(Name, Email, Number, Password, firebaseAuth.getUid());
                        firebaseDatabase.getReference().child("User").child(Objects.requireNonNull(firebaseAuth.getUid())).setValue(user);
                        Toast.makeText(SignUpActivity.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();

                    }
                });

            }
        }).start();


    }
}