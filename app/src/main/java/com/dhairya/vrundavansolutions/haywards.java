package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class haywards extends AppCompatActivity {
    private static final String TAG = "haywardsActivity";
    private DatabaseReference orderRef,currentOrderRef;

    private String phone,phoneNumber;
    private DatabaseReference newOrderRef;
    private TextView showbottle1 , showbottle2;
    private int haywards_300_quantity = 0 , haywards_750_quantity = 0;
    private Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haywards);


        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        if(phone!=null) {
            currentOrderRef = orderRef.child(phone);// Initialize with null
        }
        showbottle2 = findViewById(R.id.show_bottle2);
        showbottle1 = findViewById(R.id.show_bottle1);

        //cart
        cart = findViewById(R.id.button5);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(haywards.this, cart.class);
                if(phone!=null) {
                    cart_intent.putExtra("phone", phone);
                }
                startActivity(cart_intent);
            }
        });

        if(currentOrderRef!=null){
            currentOrderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer haywards750Integer = snapshot.child("Haywards 750ml").getValue(Integer.class);
                    Integer haywards300Integer = snapshot.child("Haywards 300ml").getValue(Integer.class);

                    haywards_750_quantity = haywards750Integer != null ? haywards750Integer.intValue() : 0;
                    haywards_300_quantity = haywards300Integer != null ? haywards300Integer.intValue() : 0;

                    showbottle2.setText(String.valueOf(haywards750Integer != null ? haywards750Integer:0));
                    showbottle1.setText(String.valueOf(haywards300Integer != null ? haywards300Integer:0));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "onCancelled: " + error.getMessage());
                }
            });
        }

        //profile
        Button profilebtn = findViewById(R.id.profile_haywards);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(haywards.this, profile.class);
                if(phone!=null){
                    profile_intent.putExtra("Phone",phone);
                }
                startActivity(profile_intent);
            }
        });





        //300 ml
        Button plusbottle1 = findViewById(R.id.plus_bottle_1);
        Button minusbottle1 = findViewById(R.id.minus_bottle1);


        plusbottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(haywards_300_quantity==0){
                    addHaywards300ToOrder();
                }
                else {
                    incrementHaywards300Quantity();
                }
                showbottle1.setText(String.valueOf(haywards_300_quantity));
            }
        });

        minusbottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementHaywards300Quantity();
                showbottle1.setText(String.valueOf(haywards_300_quantity));
            }
        });




        //750 ml
        Button plusbottle2 = findViewById(R.id.plus_bottle_2);
        Button minusbottle2 = findViewById(R.id.minus_bottle2);




        plusbottle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(haywards_750_quantity==0){
                    addHaywards750ToOrder();
                }
                else {
                    incrementHaywards750Quantity();
                }
                showbottle2.setText(String.valueOf(haywards_750_quantity));
            }
        });

        minusbottle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementHaywards750Quantity();
                showbottle2.setText(String.valueOf(haywards_750_quantity));
            }
        });

    }

    private void incrementHaywards750Quantity() {
        haywards_750_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Haywards 750ml").setValue(haywards_750_quantity);
        }
    }

    private void decrementHaywards750Quantity() {
        if (haywards_750_quantity > 0) {
            haywards_750_quantity--;
            if (currentOrderRef != null) {
                if (haywards_750_quantity > 0) {
                    currentOrderRef.child("Haywards 750ml").setValue(haywards_750_quantity);
                } else {
                    currentOrderRef.child("Haywards 750ml").removeValue();
                }
            }
        }
    }

    private void addHaywards750ToOrder() {
        // Create a new Order object with the product details
        haywards.Order order = new haywards.Order("Haywards 750ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        if(phone!=null) {
            phoneNumber = phone;
        }

        // Create a child node for this order using the phone number and set its values
        if(phoneNumber!=null) {
            DatabaseReference newOrderRef = orderRef.child(phoneNumber);
        }
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        // Create a child node for this order and set its values
        newOrderRef.child("Haywards 750ml").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        haywards_750_quantity = order.getQuantity();
        showbottle2.setText(String.valueOf(haywards_750_quantity));
    }

    private void decrementHaywards300Quantity() {
        if (haywards_300_quantity > 0) {
            haywards_300_quantity--;
            if (currentOrderRef != null) {
                if (haywards_300_quantity > 0) {
                    currentOrderRef.child("Haywards 300ml").setValue(haywards_300_quantity);
                }
                else {
                    currentOrderRef.child("Haywards 300ml").removeValue();
                }
            }
        }
    }

    private void incrementHaywards300Quantity() {
        haywards_300_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Haywards 300ml").setValue(haywards_300_quantity);
        }
    }

    private void addHaywards300ToOrder() {
        // Create a new Order object with the product details
        haywards.Order order = new haywards.Order("Haywards 300ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        if(phone!=null) {
            phoneNumber = phone;
        }

        // Create a child node for this order using the phone number and set its values

        if(phoneNumber!=null) {
            newOrderRef = orderRef.child(phoneNumber);
        }

        // Create a child node for this order and set its values
        newOrderRef.child("Haywards 300ml").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        haywards_300_quantity = order.getQuantity();
        showbottle1.setText(String.valueOf(haywards_300_quantity));
    }
    private static class Order {
        private String productName;
        private int quantity;

        public Order() {
            // Default constructor required for Firebase serialization
        }

        public Order(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}