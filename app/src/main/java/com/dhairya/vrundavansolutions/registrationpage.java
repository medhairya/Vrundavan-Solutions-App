package com.dhairya.vrundavansolutions;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registrationpage extends AppCompatActivity {

    private EditText InputName, InputShopName, InputShopAddress, InputPhone, InputPassword;
    Button RegisterButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratiopage);

        RegisterButton = findViewById(R.id.update_button);
        InputName = findViewById(R.id.inputName);
        InputShopName = findViewById(R.id.inputShopName);
        InputShopAddress = findViewById(R.id.inputShopAddress);
        InputPhone = findViewById(R.id.inputPhoneNumber);
        InputPassword = findViewById(R.id.inputPassword);
        loadingBar = new ProgressDialog(this);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String Name = InputName.getText().toString();
        String ShopName = InputShopName.getText().toString();
        String ShopAddress = InputShopAddress.getText().toString();
        String PhoneNumber = InputPhone.getText().toString();
        String Password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ShopName)) {
            Toast.makeText(this, "Please Enter Shop Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ShopAddress)) {
            Toast.makeText(this, "Please Enter Shop Address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PhoneNumber)) {
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Creating Your New Account");
            loadingBar.setMessage("Loading...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(PhoneNumber, Password, ShopAddress, Name, ShopName);
        }
    }

    public void ValidatephoneNumber(String phoneNumber, String password, String shopAddress, String name, String shopName) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phoneNumber).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Phone", phoneNumber);
                    userdataMap.put("Password", password);
                    userdataMap.put("Shop_Address", shopAddress);
                    userdataMap.put("Name", name);
                    userdataMap.put("Shop_Name", shopName);

                    RootRef.child("Users").child(phoneNumber).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registrationpage.this, "Congratulations, You have created your new Account", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(registrationpage.this, loginpage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(registrationpage.this, "Phone Number Already Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(registrationpage.this, loginpage.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(registrationpage.this, "This " + phoneNumber + " Already Exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                    Intent intent = new Intent(registrationpage.this, loginpage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingBar.dismiss();
                Toast.makeText(registrationpage.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
