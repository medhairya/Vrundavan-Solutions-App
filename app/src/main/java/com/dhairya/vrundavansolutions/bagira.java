package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bagira extends AppCompatActivity {

    private DatabaseReference orderRef;
    private TextView quantityTextView;
    private int quantity = 0;
    private DatabaseReference currentOrderRef;

    private String currentOrderNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagira);


        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        currentOrderRef = null; // Initialize with null


        Button plusBagiraBottleButton = findViewById(R.id.plus_bagira_bottle);
        Button minusBagiraBottleButton = findViewById(R.id.minus_bagira_bottle);


        quantityTextView = findViewById(R.id.show_bagira_bottle);
        plusBagiraBottleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the product is already added
                if (quantity == 0) {
                    // Product is not added, so add it
                    addProductToOrder();
                } else {
                    // Product is already added, so increment the quantity
                    incrementQuantity();
                }

                // Update the quantity in the TextView
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        minusBagiraBottleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementQuantity();
                quantityTextView.setText(String.valueOf(quantity));
            }
        });

        //profile
        Button profilebtn = findViewById(R.id.profile_bagira);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(bagira.this, profile.class);
                startActivity(profile_intent);
            }
        });
    }

    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            updateQuantityInOrder();
        }
    }

    private void updateQuantityInOrder() {
            if (currentOrderRef != null) {
                currentOrderRef.child("quantity").setValue(quantity);
            }
    }

    private void addProductToOrder() {

        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Bottle", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = "9662447873";


        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child("productName").setValue(order.getProductName());
        newOrderRef.child("quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        quantity = order.getQuantity();
        quantityTextView.setText(String.valueOf(quantity));
    }

    private void incrementQuantity() {
        // Increment the quantity variable
        quantity++;

        // Update the quantity in the Order database
        currentOrderRef.child("quantity").setValue(quantity);
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
