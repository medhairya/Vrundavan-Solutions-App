package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
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

public class gina extends AppCompatActivity {

    int jeera_quantity = 0, orrange_quantity= 0 , t2_quantity= 0 , limca_quantity= 0 , c10_quantity= 0 , red_apple_quantity = 0 , green_apple_quantity=0;

    private String phone,phoneNumber;
    private DatabaseReference orderRef,currentOrderRef,newOrderRef;
    private Button cart;

    private TextView showjeera,showorrange,showt2,showlimca,showc10,showredapple,showgreenapple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gina);

        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        if(phone!=null) {
            currentOrderRef = orderRef.child(phone);// Initialize with null
            phoneNumber=phone;
        }


        showgreenapple = findViewById(R.id.show_bottle14);
        showredapple = findViewById(R.id.show_bottle13);
        showc10 = findViewById(R.id.show_bottle12);
        showlimca = findViewById(R.id.show_bottle11);
        showt2 = findViewById(R.id.show_bottle10);
        showorrange = findViewById(R.id.show_bottle9);
        showjeera = findViewById(R.id.show_bottle8);

        if(currentOrderRef!=null) {
            currentOrderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer greenAppleInteger = snapshot.child("Gina Green Apple").getValue(Integer.class);
                    Integer redAppleInteger = snapshot.child("Gina Red Apple").getValue(Integer.class);
                    Integer c10Integer = snapshot.child("Gina C10").getValue(Integer.class);
                    Integer limcaInteger = snapshot.child("Gina Limca").getValue(Integer.class);
                    Integer t2Integer = snapshot.child("Gina T2").getValue(Integer.class);
                    Integer orangeInteger = snapshot.child("Gina Orange").getValue(Integer.class);
                    Integer jeeraInteger = snapshot.child("Gina Jeera").getValue(Integer.class);

                    green_apple_quantity = greenAppleInteger != null ? greenAppleInteger.intValue() : 0;
                    red_apple_quantity = redAppleInteger != null ? redAppleInteger.intValue() : 0;
                    c10_quantity = c10Integer != null ? c10Integer.intValue() : 0;
                    limca_quantity = limcaInteger != null ? limcaInteger.intValue() : 0;
                    t2_quantity = t2Integer != null ? t2Integer.intValue() : 0;
                    orrange_quantity = orangeInteger != null ? orangeInteger.intValue() : 0;
                    jeera_quantity = jeeraInteger != null ? jeeraInteger.intValue() : 0;

                    showgreenapple.setText(String.valueOf(green_apple_quantity));
                    showredapple.setText(String.valueOf(red_apple_quantity));
                    showc10.setText(String.valueOf(c10_quantity));
                    showlimca.setText(String.valueOf(limca_quantity));
                    showt2.setText(String.valueOf(t2_quantity));
                    showorrange.setText(String.valueOf(orrange_quantity));
                    showjeera.setText(String.valueOf(jeera_quantity));


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        //profile
        Button profilebtn = findViewById(R.id.profile_gina);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(gina.this, profile.class);
                if(phone!=null){
                    profile_intent.putExtra("Phone",phone);
                }
                startActivity(profile_intent);
            }
        });


        //cart
        cart = findViewById(R.id.cart_gina);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(gina.this, cart.class);
                if(phone!=null) {
                    cart_intent.putExtra("phone", phone);
                }
                startActivity(cart_intent);
            }
        });

        //jeera
        Button plusjeerabutton =findViewById(R.id.plus_bottle_8);
        Button minusjeerabutton =findViewById(R.id.minus_bottle8);


        plusjeerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showjeera.setText(String.valueOf(jeera_quantity));
                if(jeera_quantity==0){
                    addjeeraToOrder();
                }
                else {
                    incrementjeeraQuantity();
                }
                showjeera.setText(String.valueOf(jeera_quantity));
            }
        });

        minusjeerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementjeeraQuantity();
                showjeera.setText(String.valueOf(jeera_quantity));
            }
        });

        //orange
        Button plusorangebutton =findViewById(R.id.plus_bottle_9);
        Button minusorangebutton =findViewById(R.id.minus_bottle9);


        plusorangebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orrange_quantity==0){
                    addorrangeToOrder();
                }
                else {
                    incrementorrangeQuantity();
                }
                showorrange.setText(String.valueOf(orrange_quantity));
            }
        });

        minusorangebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementorrangeQuantity();
                showorrange.setText(String.valueOf(orrange_quantity));
            }
        });

        //t2
        Button plust2button =findViewById(R.id.plus_bottle_11);
        Button minust2button =findViewById(R.id.minus_bottle10);


        plust2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t2_quantity==0){
                    addt2ToOrder();
                }
                else {
                    incrementt2Quantity();
                }
                showt2.setText(String.valueOf(t2_quantity));
            }
        });

        minust2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementt2Quantity();
                showt2.setText(String.valueOf(t2_quantity));
            }
        });


        //limca
        Button pluslimcabutton =findViewById(R.id.plus_bottle_10);
        Button minuslimcabutton =findViewById(R.id.minus_bottle11);



        pluslimcabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(limca_quantity==0){
                    addlimcaToOrder();
                }
                else {
                    incrementlimcaQuantity();
                }
                showlimca.setText(String.valueOf(limca_quantity));
            }
        });

        minuslimcabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementlimcaQuantity();
                showlimca.setText(String.valueOf(limca_quantity));
            }
        });

        //limca
        Button plusc10button =findViewById(R.id.plus_bottle_12);
        Button minusc10button =findViewById(R.id.minus_bottle12);


        plusc10button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c10_quantity==0){
                    addc10ToOrder();
                }
                else {
                    incrementc10Quantity();
                }
                showc10.setText(String.valueOf(c10_quantity));
            }
        });

        minusc10button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementc10Quantity();
                showc10.setText(String.valueOf(c10_quantity));
            }
        });

        //red_apple
        Button plusredapplebutton =findViewById(R.id.plus_bottle_13);
        Button minusredapplebutton =findViewById(R.id.minus_bottle13);


        plusredapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(red_apple_quantity==0){
                    addred_appleToOrder();
                }
                else {
                    incrementred_appleQuantity();
                }
                showredapple.setText(String.valueOf(red_apple_quantity));
            }
        });

        minusredapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementredappleQuantity();
                showredapple.setText(String.valueOf(red_apple_quantity));
            }
        });


        //green_apple
        Button plusgreenapplebutton =findViewById(R.id.plus_bottle_14);
        Button minusgreenapplebutton =findViewById(R.id.minus_bottle14);


        plusgreenapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(green_apple_quantity==0){
                    addgreen_appleToOrder();
                }
                else {
                    incrementgreen_appleQuantity();
                }
                showgreenapple.setText(String.valueOf(green_apple_quantity));
            }
        });

        minusgreenapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementgreenappleQuantity();
                showgreenapple.setText(String.valueOf(green_apple_quantity));
            }
        });

    }

    private void addgreen_appleToOrder() {
        gina.Order order = new gina.Order("Gina Green Apple", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Gina Green Apple").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        green_apple_quantity = order.getQuantity();
        showgreenapple.setText(String.valueOf(green_apple_quantity));
    }

    private void decrementgreenappleQuantity() {
        if (green_apple_quantity > 0) {
            green_apple_quantity--;
            if (currentOrderRef != null) {
                if (green_apple_quantity > 0) {
                    currentOrderRef.child("Gina Green Apple").setValue(green_apple_quantity);
                }
                else {
                    currentOrderRef.child("Gina Green Apple").removeValue();
                }
            }
        }
    }

    private void incrementgreen_appleQuantity() {
        green_apple_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina Green Apple").setValue(green_apple_quantity);
        }
    }

    private void addred_appleToOrder() {

        gina.Order order = new gina.Order("Gina Red Apple", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Gina Red Apple").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        red_apple_quantity = order.getQuantity();
        showredapple.setText(String.valueOf(red_apple_quantity));
    }

    private void incrementred_appleQuantity() {
        red_apple_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina Red Apple").setValue(red_apple_quantity);
        }
    }

    private void decrementredappleQuantity() {
        if (red_apple_quantity > 0) {
            red_apple_quantity--;
            if (currentOrderRef != null) {
                if (red_apple_quantity > 0) {
                    currentOrderRef.child("Gina Red Apple").setValue(red_apple_quantity);
                }
                else {
                    currentOrderRef.child("Gina Red Apple").removeValue();
                }
            }
        }
    }

    private void addc10ToOrder() {
        gina.Order order = new gina.Order("Gina C-10", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Gina C-10").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        c10_quantity = order.getQuantity();
        showc10.setText(String.valueOf(c10_quantity));
    }

    private void decrementc10Quantity() {
        if (c10_quantity > 0) {
            c10_quantity--;
            if (currentOrderRef != null) {
                if (c10_quantity > 0) {
                    currentOrderRef.child("Gina C-10").setValue(c10_quantity);
                }
                else {
                    currentOrderRef.child("Gina C-10").removeValue();
                }
            }
        }
    }

    private void incrementc10Quantity() {
        c10_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina C-10").setValue(c10_quantity);
        }
    }

    private void decrementlimcaQuantity() {
        if (limca_quantity > 0) {
            limca_quantity--;
            if (currentOrderRef != null) {
                if (limca_quantity > 0) {
                    currentOrderRef.child("Gina Limca").setValue(limca_quantity);
                }
                else {
                    currentOrderRef.child("Gina Limca").removeValue();
                }
            }
        }
    }

    private void incrementlimcaQuantity() {
        limca_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina Limca").setValue(limca_quantity);
        }
    }

    private void addlimcaToOrder() {
        gina.Order order = new gina.Order("Gina Limca", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Gina Limca").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        limca_quantity = order.getQuantity();
        showlimca.setText(String.valueOf(limca_quantity));
    }

    private void addt2ToOrder() {
        gina.Order order = new gina.Order("Gina T2", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Gina T2").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        t2_quantity = order.getQuantity();
        showt2.setText(String.valueOf(t2_quantity));
    }

    private void decrementt2Quantity() {
        if (t2_quantity > 0) {
            t2_quantity--;
            if (currentOrderRef != null) {
                if (t2_quantity > 0) {
                    currentOrderRef.child("Gina T2").setValue(t2_quantity);
                }
                else {
                    currentOrderRef.child("Gina T2").removeValue();
                }
            }
        }
    }

    private void incrementt2Quantity() {
        t2_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina T2").setValue(t2_quantity);
        }
    }

    private void incrementorrangeQuantity() {
        orrange_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina Orange").setValue(orrange_quantity);
        }
    }

    private void addorrangeToOrder() {
        gina.Order order = new gina.Order("Gina Orange", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();

        if(phone!=null) {
            phoneNumber = phone;
        }

        if(phoneNumber!=null) {
            newOrderRef = orderRef.child(phoneNumber);
        }

        newOrderRef.child("Gina Orange").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        orrange_quantity = order.getQuantity();
        showorrange.setText(String.valueOf(orrange_quantity));
    }

    private void decrementorrangeQuantity() {
        if (orrange_quantity > 0) {
            orrange_quantity--;
            if (currentOrderRef != null) {
                if (orrange_quantity > 0) {
                    currentOrderRef.child("Gina Orange").setValue(orrange_quantity);
                }
                else {
                    currentOrderRef.child("Gina Orange").removeValue();
                }
            }
        }
    }

    private void decrementjeeraQuantity() {
        if (jeera_quantity > 0) {
            jeera_quantity--;
            if (currentOrderRef != null) {
                if (jeera_quantity > 0) {
                    currentOrderRef.child("Gina Jeera").setValue(jeera_quantity);
                }
                else {
                    currentOrderRef.child("Gina Jeera").removeValue();
                }
            }
        }
    }

    private void incrementjeeraQuantity() {
        jeera_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Gina Jeera").setValue(jeera_quantity);
        }
    }

    private void addjeeraToOrder() {
        gina.Order order = new gina.Order("Gina Jeera", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        if(phoneNumber!=null) {
            DatabaseReference newOrderRef = orderRef.child(phoneNumber);


            newOrderRef.child("Gina Jeera").setValue(order.getQuantity());

            currentOrderRef = newOrderRef;

            jeera_quantity = order.getQuantity();
            showjeera.setText(String.valueOf(jeera_quantity));
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