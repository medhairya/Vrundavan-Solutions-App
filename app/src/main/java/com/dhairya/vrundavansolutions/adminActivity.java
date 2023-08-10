package com.dhairya.vrundavansolutions;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OuterAdapter outerAdapter;
    private boolean showCompletedButton = true; // Initially, show the Completed button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activty);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        outerAdapter = new OuterAdapter(new ArrayList<>(), FirebaseDatabase.getInstance().getReference("Products"));
        recyclerView.setAdapter(outerAdapter);

        Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Toggle the showCompletedButton based on the Switch state
                showCompletedButton = !isChecked;

                // Fetch and display orders based on the Switch state
                if (isChecked) {
                    fetchAndDisplayPastOrders();
                }
                else {
                    fetchAndDisplayPendingOrders();
                }
            }
        });

        // Initially, display "Pending Orders"
        fetchAndDisplayPendingOrders();
    }

    private void fetchAndDisplayPendingOrders() {
        DatabaseReference currentOrderRef = FirebaseDatabase.getInstance().getReference("Current Order");
        currentOrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Order> orderList = new ArrayList<>();
                for (DataSnapshot phoneNumberSnapshot : dataSnapshot.getChildren()) {
                    String phoneNumber = phoneNumberSnapshot.getKey();
                    List<InnerAdapter.OrderedItem> orderedItems = new ArrayList<>();
                    for (DataSnapshot itemSnapshot : phoneNumberSnapshot.getChildren()) {
                        String itemName = itemSnapshot.getKey();
                        String quantityString = itemSnapshot.getValue(String.class);

                        // Handle parsing the quantity as a String
                        try {
                            int quantity = Integer.parseInt(quantityString);
                            InnerAdapter.OrderedItem orderedItem = new InnerAdapter.OrderedItem(itemName, quantity);
                            orderedItems.add(orderedItem);
                        }
                        catch (NumberFormatException e) {
                            // Skip the item with invalid quantity value
                            Log.w("adminActivity", "Invalid quantity value for item: " + itemName);
                        }
                    }

                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    usersRef.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                            if (userSnapshot.exists()) {
                                String name = userSnapshot.child("Name").getValue(String.class);
                                String shopName = userSnapshot.child("Shop_Name").getValue(String.class);
                                String address = userSnapshot.child("Shop_Address").getValue(String.class);
                                Order order = new Order(name, phoneNumber, shopName, address, orderedItems);
                                orderList.add(order);
                                outerAdapter.setOrderList(orderList);
                                outerAdapter.setShowCompletedButton(showCompletedButton);
                                outerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
            }
        });
        outerAdapter.setShowCompletedButton(true);
        outerAdapter.notifyDataSetChanged();
    }

    private void fetchAndDisplayPastOrders() {
        DatabaseReference pastOrdersRef = FirebaseDatabase.getInstance().getReference("Past Orders");
        pastOrdersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Order> orderList = new ArrayList<>();
                for (DataSnapshot phoneNumberSnapshot : dataSnapshot.getChildren()) {
                    String phoneNumber = phoneNumberSnapshot.getKey();
                    List<InnerAdapter.OrderedItem> orderedItems = new ArrayList<>();
                    for (DataSnapshot itemSnapshot : phoneNumberSnapshot.getChildren()) {
                        String itemName = itemSnapshot.getKey();
                        String quantityString = itemSnapshot.getValue(String.class);

                        // Handle parsing the quantity as a String
                        try {
                            int quantity = Integer.parseInt(quantityString);
                            InnerAdapter.OrderedItem orderedItem = new InnerAdapter.OrderedItem(itemName, quantity);
                            orderedItems.add(orderedItem);
                        }
                        catch (NumberFormatException e) {
                            // Skip the item with invalid quantity value
                            Log.w("adminActivity", "Invalid quantity value for item: " + itemName);
                        }
                    }

                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    usersRef.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                            if (userSnapshot.exists()) {
                                String name = userSnapshot.child("Name").getValue(String.class);
                                String shopName = userSnapshot.child("Shop_Name").getValue(String.class);
                                String address = userSnapshot.child("Shop_Address").getValue(String.class);
                                Order order = new Order(name, phoneNumber, shopName, address, orderedItems);
                                orderList.add(order);
                                outerAdapter.setOrderList(orderList);
                                outerAdapter.setShowCompletedButton(showCompletedButton);
                                outerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
            }
        });
        outerAdapter.setShowCompletedButton(false);
        outerAdapter.notifyDataSetChanged();
    }
}
