package com.dhairya.vrundavansolutions;

import android.app.ProgressDialog;
import android.content.Intent;
import  android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dhairya.vrundavansolutions.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginpage extends AppCompatActivity {

    private EditText PhoneNumber, Password ;
    Button LoginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        PhoneNumber = findViewById(R.id.ePhoneNumber);
        Password = findViewById(R.id.ePassword);
        LoginButton = findViewById(R.id.eLogin);
        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });


    }

    private void LoginUser() {
        String phone = PhoneNumber.getText().toString();
        String password = Password.getText().toString();


        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Loging to Your Account");
            loadingBar.setMessage("Loading...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone,password);
        }

    }

    private void AllowAccessToAccount(String phone, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(phone).exists()){
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone)){
                        if (usersData.getPassword().equals(password)){

                            Toast.makeText(loginpage.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(loginpage.this, home.class);
                            startActivity(intent);
                        }

                    }
                }
                else {
                    Toast.makeText(loginpage.this, "Phone Number "+phone+" do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
