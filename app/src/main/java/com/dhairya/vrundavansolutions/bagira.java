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

public class bagira extends AppCompatActivity {
    private static final String TAG = "bagiraActivity";


    private DatabaseReference orderRef, currentOrderRef;
    private String phone;
    private TextView showCanRed, showBottle, showWater200, showWater500, showWater1000;
    private int canRedQuantity = 0, bottleQuantity = 0, water200Quantity = 0, water500Quantity = 0, water1000Quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagira);

        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        currentOrderRef = orderRef.child(phone); // Retrieve the existing order for the user

        showCanRed=findViewById(R.id.show_bagira_can_red);
        showBottle = findViewById(R.id.show_bagira_bottle);
        showWater1000 = findViewById(R.id.show_bagira_can_red4);
        showWater500 = findViewById(R.id.show_bagira_can_red3);
        showWater200 = findViewById(R.id.show_bagira_can_red2);



        if (currentOrderRef != null) {
            // Retrieve the existing quantities from the order and update the TextViews
            currentOrderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Integer canRedQuantityInteger = dataSnapshot.child("Black Bagira Can").getValue(Integer.class);
                    Integer bottleQuantityInteger = dataSnapshot.child("Black Bagira Bottle").getValue(Integer.class);
                    Integer water200QuantityInteger = dataSnapshot.child("Black Bagira Water 200ml").getValue(Integer.class);
                    Integer water500QuantityInteger = dataSnapshot.child("Black Bagira Water 500ml").getValue(Integer.class);
                    Integer water1000QuantityInteger = dataSnapshot.child("Black Bagira Water 1000ml").getValue(Integer.class);

                    canRedQuantity = canRedQuantityInteger != null ? canRedQuantityInteger.intValue() : 0;
                    bottleQuantity = bottleQuantityInteger != null ? bottleQuantityInteger.intValue() : 0;
                    water200Quantity = water200QuantityInteger != null ? water200QuantityInteger.intValue() : 0;
                    water500Quantity = water500QuantityInteger != null ? water500QuantityInteger.intValue() : 0;
                    water1000Quantity = water1000QuantityInteger != null ? water1000QuantityInteger.intValue() : 0;

                    showCanRed.setText(String.valueOf(canRedQuantity));
                    showBottle.setText(String.valueOf(bottleQuantity));
                    showWater200.setText(String.valueOf(water200Quantity));
                    showWater500.setText(String.valueOf(water500Quantity));
                    showWater1000.setText(String.valueOf(water1000Quantity));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled: " + databaseError.getMessage());
                }
            });
        }



        //profile
        Button profilebtn = findViewById(R.id.profile_bagira);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(bagira.this, profile.class);
                startActivity(profile_intent);
            }
        });

        // Red Can
        Button plusCanRed = findViewById(R.id.plus_bagira_can_red);
        Button minusCanRed = findViewById(R.id.minus_bagira_can_red);

        plusCanRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canRedQuantity==0){
                    addCanRedToOrder();
                }
                else {
                    incrementCanRedQuantity();
                }
                showCanRed.setText(String.valueOf(canRedQuantity));
            }
        });

        minusCanRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementCanRedQuantity();
                showCanRed.setText(String.valueOf(canRedQuantity));
            }
        });

        // Bagira Bottle
        Button plusBottle = findViewById(R.id.plus_bagira_bottle);
        Button minusBottle = findViewById(R.id.minus_bagira_bottle);

        plusBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bottleQuantity==0){
                    addBottleToOrder();
                }
                else {
                    incrementBottleQuantity();
                }
                showBottle.setText(String.valueOf(bottleQuantity));
            }
        });

        minusBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementBottleQuantity();
                showBottle.setText(String.valueOf(bottleQuantity));
            }
        });

        // Water 200ml
        Button plusWater200 = findViewById(R.id.plus_bagira_can_red2);
        Button minusWater200 = findViewById(R.id.minus_bagira_can_red2);

        plusWater200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(water200Quantity==0){
                    addWater200ToOrder();
                }
                else {
                    incrementWater200Quantity();
                }
                showWater200.setText(String.valueOf(water200Quantity));
            }
        });

        minusWater200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementWater200Quantity();
                showWater200.setText(String.valueOf(water200Quantity));
            }
        });

        // Water 500ml
        Button plusWater500 = findViewById(R.id.plus_bagira_can_red4);
        Button minusWater500 = findViewById(R.id.minus_bagira_can_red3);



        plusWater500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(water500Quantity==0){
                    addWater500ToOrder();
                }
                else {
                    incrementWater500Quantity();
                }
                showWater500.setText(String.valueOf(water500Quantity));
            }
        });

        minusWater500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementWater500Quantity();
                showWater500.setText(String.valueOf(water500Quantity));
            }
        });

        // Water 1000ml
        Button plusWater1000 = findViewById(R.id.plus_bagira_can_red5);
        Button minusWater1000 = findViewById(R.id.minus_bagira_can_red4);

        plusWater1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(water1000Quantity==0){
                    addWater1000ToOrder();
                }
                else {
                    incrementWater1000Quantity();
                }
                showWater1000.setText(String.valueOf(water1000Quantity));
            }
        });

        minusWater1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementWater1000Quantity();
                showWater1000.setText(String.valueOf(water1000Quantity));
            }
        });
    }

    private void incrementWater1000Quantity() {
        water1000Quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Black Bagira Water 1000ml").setValue(water1000Quantity);
        }
    }

    private void decrementWater1000Quantity() {
        if (water1000Quantity > 0) {
            water1000Quantity--;
            if (currentOrderRef != null) {
                if (water1000Quantity > 0) {
                    currentOrderRef.child("Black Bagira Water 1000ml").setValue(water1000Quantity);
                }
                else {
                    currentOrderRef.child("Black Bagira Water 1000ml").removeValue();
                }
            }
        }
    }

    private void addWater1000ToOrder() {
        // Create a new Order object with the product details
        bagira.Order order = new bagira.Order("Black Bagira Water 1000ml", 1);

        // Add the order to the Order database
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        // Create a child node for this order using the phone number and set its values
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        // Create a child node for this order and set its values
        newOrderRef.child("Black Bagira Water 1000ml").setValue(order.getQuantity());

        // Update the currentOrderRef to point to the newly created node
        currentOrderRef = newOrderRef;

        // Get the quantity of the order and update the TextView
        water1000Quantity = order.getQuantity();
        showWater1000.setText(String.valueOf(water1000Quantity));
    }

    private void decrementWater500Quantity() {
        if (water500Quantity > 0) {
            water500Quantity--;
            if (currentOrderRef != null) {
                if (water500Quantity > 0) {
                    currentOrderRef.child("Black Bagira Water 500ml").setValue(water500Quantity);
                }
                else {
                    currentOrderRef.child("Black Bagira Water 500ml").removeValue();
                }
            }
        }
    }

    private void incrementWater500Quantity() {
        water500Quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Black Bagira Water 500ml").setValue(water500Quantity);
        }
    }

    private void addWater500ToOrder() {
        bagira.Order order = new bagira.Order("Black Bagira Water 500ml", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Black Bagira Water 500ml").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        water500Quantity = order.getQuantity();
        showWater500.setText(String.valueOf(water500Quantity));
    }

    private void decrementWater200Quantity() {
        if (water200Quantity > 0) {
            water200Quantity--;
            if (currentOrderRef != null) {
                if (water200Quantity > 0) {
                    currentOrderRef.child("Black Bagira Water 200ml").setValue(water200Quantity);
                }
                else {
                    currentOrderRef.child("Black Bagira Water 200ml").removeValue();
                }
            }
        }
    }

    private void incrementWater200Quantity() {
        water200Quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Black Bagira Water 200ml").setValue(water200Quantity);
        }
    }

    private void addWater200ToOrder() {
        bagira.Order order = new bagira.Order("Black Bagira Water 200ml", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Black Bagira Water 200ml").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        water200Quantity = order.getQuantity();
        showWater200.setText(String.valueOf(water200Quantity));
    }

    private void decrementBottleQuantity() {
        if (bottleQuantity > 0) {
            bottleQuantity--;
            if (currentOrderRef != null) {
                if (bottleQuantity > 0) {
                    currentOrderRef.child("Black Bagira Bottle").setValue(bottleQuantity);
                }
                else {
                    currentOrderRef.child("Black Bagira Bottle").removeValue();
                }
            }
        }
    }

    private void incrementBottleQuantity() {
        bottleQuantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Black Bagira Bottle").setValue(bottleQuantity);
        }
    }

    private void addBottleToOrder() {
        bagira.Order order = new bagira.Order("Black Bagira Bottle", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Black Bagira Bottle").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        bottleQuantity = order.getQuantity();
        showBottle.setText(String.valueOf(bottleQuantity));
    }

    private void decrementCanRedQuantity() {
        if (canRedQuantity > 0) {
            canRedQuantity--;
            if (currentOrderRef != null) {
                if (canRedQuantity > 0) {
                    currentOrderRef.child("Black Bagira Can").setValue(canRedQuantity);
                }
                else {
                    currentOrderRef.child("Black Bagira Can").removeValue();
                }
            }
        }

    }

    private void incrementCanRedQuantity() {
        canRedQuantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Black Bagira Can").setValue(canRedQuantity);
        }
    }

    private void addCanRedToOrder() {
        bagira.Order order = new bagira.Order("Black Bagira Can", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Black Bagira Can").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        canRedQuantity = order.getQuantity();
        showCanRed.setText(String.valueOf(canRedQuantity));
    }

    private void updateQuantity(String item, int quantity) {
        if (currentOrderRef != null) {
            currentOrderRef.child("Product").child(item).setValue(quantity);
        }
    }

    private void removeQuantity(String item) {
        if (currentOrderRef != null) {
            currentOrderRef.child("Product").child(item).removeValue();
        }
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
