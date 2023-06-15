package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class haywards extends AppCompatActivity {

    private DatabaseReference orderRef,currentOrderRef;
    private String phone;
    private TextView showbottle1 , showbottle2;
    private int haywards_300_quantity = 0 , haywards_750_quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haywards);

        //profile
        Button profilebtn = findViewById(R.id.profile_haywards);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(haywards.this, profile.class);
                startActivity(profile_intent);
            }
        });


        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        currentOrderRef = null; // Initialize with null


        //300 ml
        Button plusbottle1 = findViewById(R.id.plus_bottle_1);
        Button minusbottle1 = findViewById(R.id.minus_bottle1);
        showbottle1 = findViewById(R.id.show_bottle1);


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
        showbottle2 = findViewById(R.id.show_bottle2);


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
        currentOrderRef.child("Haywards_750ml_Quantity").setValue(haywards_750_quantity);
    }

    private void decrementHaywards750Quantity() {
        if (haywards_750_quantity > 0) {
            haywards_750_quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Haywards_750ml_Quantity").setValue(haywards_750_quantity);
            }
        }
    }

    private void addHaywards750ToOrder() {
        // Create a new Order object with the product details
        haywards.Order order = new haywards.Order("Haywards 750ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName7").setValue(order.getProductName());
        newOrderRef.child("Haywards_750ml_Quantity").setValue(order.getQuantity());

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
                currentOrderRef.child("Haywards_300ml_Quantity").setValue(haywards_300_quantity);
            }
        }
    }

    private void incrementHaywards300Quantity() {
        haywards_300_quantity++;
        currentOrderRef.child("Haywards_300ml_Quantity").setValue(haywards_300_quantity);
    }

    private void addHaywards300ToOrder() {
        // Create a new Order object with the product details
        haywards.Order order = new haywards.Order("Haywards 300ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName6").setValue(order.getProductName());
        newOrderRef.child("Haywards_300ml_Quantity").setValue(order.getQuantity());

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