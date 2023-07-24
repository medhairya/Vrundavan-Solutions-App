package com.dhairya.vrundavansolutions;

import android.os.Bundle;
import android.util.Log;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activty);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference currentOrderRef = database.getReference("Current Order");

        // Retrieve data from "Current Order" node
        currentOrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Use a HashMap to group ordered items by phone number
                HashMap<String, List<InnerAdapter.OrderedItem>> orderMap = new HashMap<>();

                // Loop through all phone numbers (key)
                for (DataSnapshot phoneNumberSnapshot : dataSnapshot.getChildren()) {
                    String phoneNumber = phoneNumberSnapshot.getKey();
                    List<InnerAdapter.OrderedItem> orderedItems = new ArrayList<>();

                    // Loop through all items (child nodes) under the phone number
                    for (DataSnapshot itemSnapshot : phoneNumberSnapshot.getChildren()) {
                        String itemName = itemSnapshot.getKey();
                        String quantityString = itemSnapshot.getValue(String.class); // Get quantity as a String

                        // Convert the quantityString to an integer
                        int quantity = Integer.parseInt(quantityString);

                        // Create OrderedItem and add it to the orderedItems list
                        InnerAdapter.OrderedItem orderedItem = new InnerAdapter.OrderedItem(itemName, quantity);
                        orderedItems.add(orderedItem);
                    }

                    // Add the orderedItems list to the orderMap
                    orderMap.put(phoneNumber, orderedItems);
                }

                // Create a list of orders based on the orderMap
                List<Order> orderList = new ArrayList<>();
                for (Map.Entry<String, List<InnerAdapter.OrderedItem>> entry : orderMap.entrySet()) {
                    String phoneNumber = entry.getKey();
                    List<InnerAdapter.OrderedItem> orderedItems = entry.getValue();

                    // Retrieve additional data from the "Users" node based on the phone number
                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    usersRef.child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                            if (userSnapshot.exists()) {
                                // Retrieve user details from the snapshot
                                String name = userSnapshot.child("Name").getValue(String.class);
                                String shopName = userSnapshot.child("Shop_Name").getValue(String.class);
                                String address = userSnapshot.child("Shop_Address").getValue(String.class);

                                // Create the Order object with retrieved data and add it to the list
                                Order order = new Order(name, phoneNumber, shopName, address, orderedItems);
                                orderList.add(order);

                                // Notify the adapter about the data change
                                OuterAdapter orderAdapter = new OuterAdapter(orderList);
                                recyclerView.setAdapter(orderAdapter);
                                orderAdapter.notifyDataSetChanged(); // Add this line to notify data changes

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle any errors
                            Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
                Log.e("adminActivity", "DatabaseError: " + databaseError.getMessage());
            }
        });
    }
}
