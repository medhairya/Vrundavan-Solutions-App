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
    private TextView quantity_bottle_show , quantity_redcan_show , quantity_water_200_show , quantity_water_500_show , quantity_water_1000_show;
    private int quantity = 0; //for bagira bottle
    private int can_red_quantity = 0 , water_200_quantity = 0 , water_500_quantity = 0 , water_1000_quantity = 0;
    private DatabaseReference currentOrderRef;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagira);



        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        currentOrderRef = null; // Initialize with null

        //can
        Button plusBagiraCanRed = findViewById(R.id.plus_bagira_can_red);
        Button minusBagiraCanRed = findViewById(R.id.minus_bagira_can_red);
        quantity_redcan_show = findViewById(R.id.show_bagira_can_red);

        plusBagiraCanRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(can_red_quantity==0){
                    addBagiraCanRedToOrder();
                }
                else {
                    incrementBagiraRedCanQuantity();
                }
                quantity_redcan_show.setText(String.valueOf(can_red_quantity));
            }
        });

        minusBagiraCanRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementCanRedQuantity();
                quantity_redcan_show.setText(String.valueOf(can_red_quantity));
            }
        });

        //bagira og
        Button plusBagiraBottleButton = findViewById(R.id.plus_bagira_bottle);
        Button minusBagiraBottleButton = findViewById(R.id.minus_bagira_bottle);
        quantity_bottle_show = findViewById(R.id.show_bagira_bottle);


        plusBagiraBottleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the product is already added
                if (quantity == 0) {
                    // Product is not added, so add it
                    addBagiraBottleToOrder();
                } else {
                    // Product is already added, so increment the quantity
                    incrementBagiraBottleQuantity();
                }

                // Update the quantity in the TextView
                quantity_bottle_show.setText(String.valueOf(quantity));
            }
        });

        minusBagiraBottleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementbottleQuantity();
                quantity_bottle_show.setText(String.valueOf(quantity));
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


        //water 200 ml
        Button plus_water_200_button = findViewById(R.id.plus_bagira_can_red2);
        Button minus_water_200_button = findViewById(R.id.minus_bagira_can_red2);
        quantity_water_200_show = findViewById(R.id.show_bagira_can_red2);

        plus_water_200_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_200_show.setText(String.valueOf(water_200_quantity));
                if(water_200_quantity==0){
                    addWater200ToOrder();
                }
                else {
                    incrementWater200Quantity();
                }
                quantity_water_200_show.setText(String.valueOf(water_200_quantity));
            }
        });

        minus_water_200_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_200_show.setText(String.valueOf(water_200_quantity));
                decrementWater200Quantity();
                quantity_water_200_show.setText(String.valueOf(water_200_quantity));
            }
        });





        //water 500 ml
        Button plus_water_500_button = findViewById(R.id.plus_bagira_can_red4);
        Button minus_water_500_button = findViewById(R.id.minus_bagira_can_red3);
        quantity_water_500_show = findViewById(R.id.show_bagira_can_red3);

        plus_water_500_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_500_show.setText(String.valueOf(water_500_quantity));
                if(water_500_quantity==0){
                    addWater500ToOrder();
                }
                else {
                    incrementWater500Quantity();
                }
                quantity_water_500_show.setText(String.valueOf(water_500_quantity));
            }
        });

        minus_water_500_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_500_show.setText(String.valueOf(water_500_quantity));
                decrementWater500Quantity();
                quantity_water_500_show.setText(String.valueOf(water_500_quantity));
            }
        });




        //water 1l
        Button plus_water_1000_button = findViewById(R.id.plus_bagira_can_red5);
        Button minus_water_1000_button = findViewById(R.id.minus_bagira_can_red4);
        quantity_water_1000_show = findViewById(R.id.show_bagira_can_red4);

        plus_water_1000_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));
                if(water_1000_quantity==0){
                    addWater1000ToOrder();
                }
                else {
                    incrementWater1000Quantity();
                }
                quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));
            }
        });

        minus_water_1000_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));
                decrementWater1000Quantity();
                quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));
            }
        });


    }

    private void incrementWater1000Quantity() {
        quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));
        water_1000_quantity++;
        currentOrderRef.child("Water_1000ml_Quantity").setValue(water_1000_quantity);
    }

    private void decrementWater1000Quantity() {
        if (water_1000_quantity > 0) {
            water_1000_quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Water_1000ml_Quantity").setValue(water_1000_quantity);
            }
        }
    }

    private void addWater1000ToOrder() {
        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Water 1000ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName5").setValue(order.getProductName());
        newOrderRef.child("Water_1000ml_Quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        water_1000_quantity = order.getQuantity();
        quantity_water_1000_show.setText(String.valueOf(water_1000_quantity));

    }

    private void decrementWater500Quantity() {
        if (water_500_quantity > 0) {
            water_500_quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Water_500ml_Quantity").setValue(water_500_quantity);
            }
        }
    }

    private void incrementWater500Quantity() {
        quantity_water_500_show.setText(String.valueOf(water_500_quantity));
        water_500_quantity++;
        currentOrderRef.child("Water_500ml_Quantity").setValue(water_500_quantity);
    }

    private void addWater500ToOrder() {
        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Water 500ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName4").setValue(order.getProductName());
        newOrderRef.child("Water_500ml_Quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        water_500_quantity = order.getQuantity();
        quantity_water_500_show.setText(String.valueOf(water_500_quantity));

    }

    private void decrementWater200Quantity() {
        if (water_200_quantity > 0) {
            water_200_quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Water_200ml_Quantity").setValue(water_200_quantity);
            }
        }
    }

    private void incrementWater200Quantity() {
        quantity_water_200_show.setText(String.valueOf(water_200_quantity));
        water_200_quantity++;
        currentOrderRef.child("Water_200ml_Quantity").setValue(water_200_quantity);
    }

    private void addWater200ToOrder() {
        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Water 200ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName3").setValue(order.getProductName());
        newOrderRef.child("Water_200ml_Quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        water_200_quantity = order.getQuantity();
        quantity_water_200_show.setText(String.valueOf(water_200_quantity));
    }

    private void decrementCanRedQuantity() {
        if (can_red_quantity > 0) {
            can_red_quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Red_Can_Quantity").setValue(can_red_quantity);
            }
        }
    }

    private void incrementBagiraRedCanQuantity() {
        can_red_quantity++;
        currentOrderRef.child("Red_Can_Quantity").setValue(can_red_quantity);
    }

    private void addBagiraCanRedToOrder() {
        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Red Can", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName2").setValue(order.getProductName());
        newOrderRef.child("Red_Can_Quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        can_red_quantity = order.getQuantity();
        quantity_redcan_show.setText(String.valueOf(can_red_quantity));
    }

    private void decrementbottleQuantity() {
        if (quantity > 0) {
            quantity--;
            if (currentOrderRef != null) {
                currentOrderRef.child("Black_Bagira_Quantity").setValue(quantity);
            }
        }
    }

    private void addBagiraBottleToOrder() {

        // Create a new Order object with the product details
        Order order = new Order("Black Bagira Bottle", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);


        // Create a child node for this order and set its values
        newOrderRef.child(" ProductName1").setValue(order.getProductName());
        newOrderRef.child("Black_Bagira_Quantity").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        quantity = order.getQuantity();
        quantity_bottle_show.setText(String.valueOf(quantity));
    }

    private void incrementBagiraBottleQuantity() {
        // Increment the quantity variable
        quantity++;

        // Update the quantity in the Order database
        currentOrderRef.child("Black_Bagira_Quantity").setValue(quantity);
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
