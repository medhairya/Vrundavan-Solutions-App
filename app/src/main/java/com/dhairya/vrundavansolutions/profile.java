package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
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

public class profile extends AppCompatActivity {
    private EditText editName, editPassword, editShopName, editShopAddress, editPhone;
    private DatabaseReference reference;
    private String phoneNumber;
    private Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        reference = FirebaseDatabase.getInstance().getReference();
        phoneNumber = getIntent().getStringExtra("Phone");

        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editShopName = findViewById(R.id.editShopName);
        editShopAddress = findViewById(R.id.editShopAddress);
        editPhone = findViewById(R.id.editPhoneNumber);
        Button saveButton = findViewById(R.id.update_button);


        //home
        Button home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(profile.this, home.class);
                if(phoneNumber!=null){
                    profile_intent.putExtra("phone",phoneNumber);
                }
                startActivity(profile_intent);
            }
        });


        //cart
        cart = findViewById(R.id.cart_button);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(profile.this, cart.class);
                if(phoneNumber!=null) {
                    cart_intent.putExtra("phone", phoneNumber);
                }
                startActivity(cart_intent);
            }
        });

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void showData() {
        if (reference != null) {
            DatabaseReference userRef = reference.child("Users").child(phoneNumber);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Users user = snapshot.getValue(Users.class);
                        if (user != null) {
                            editName.setText(user.getName());
                            editPassword.setText(user.getPassword());
                            editShopName.setText(user.getShop_Name());
                            editShopAddress.setText(user.getShop_Address());
                            editPhone.setText(user.getPhone());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(profile.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(profile.this, "Database reference is null", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNameChanged(String newName) {
        String currentName = getIntent().getStringExtra("Name");
        return newName != null && !newName.equals(currentName);
    }

    private void saveData() {
        String newName = editName.getText().toString().trim();
        String newPassword = editPassword.getText().toString().trim();
        String newShopName = editShopName.getText().toString().trim();
        String newShopAddress = editShopAddress.getText().toString().trim();
        //String newPhone = editPhone.getText().toString().trim();

        if (isNameChanged(newName)) {
            DatabaseReference userRef = reference.child("Users").child(phoneNumber);
            userRef.child("Name").setValue(newName);
        }
        DatabaseReference userRef = reference.child("Users").child(phoneNumber);
        userRef.child("Password").setValue(newPassword);
        userRef.child("Shop_Name").setValue(newShopName);
        userRef.child("Shop_Address").setValue(newShopAddress);
        //userRef.child("Phone").setValue(newPhone);

        Toast.makeText(profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
    }
}
