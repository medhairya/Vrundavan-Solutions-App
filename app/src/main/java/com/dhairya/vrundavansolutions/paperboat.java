package com.dhairya.vrundavansolutions;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class paperboat extends AppCompatActivity {
    private static final String TAG = "paperboatActivity";


    public ViewFlipper flipper = null;
    int mango_quantity = 0 , guava_quantity = 0 , lychee_quantity = 0 , mixfruit_quantity = 0 , apple_quantity = 0 , orange_quantity = 0;
    private String phone;
    private DatabaseReference orderRef,currentOrderRef;
    private TextView showmango,showguava,showlychee,showmixfruit,showapple,showorange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paperboat);


        phone = getIntent().getStringExtra("phone");
        orderRef = FirebaseDatabase.getInstance().getReference().child("Order");
        currentOrderRef = orderRef.child(phone); // Retrieve the existing order for the user

        showorange = findViewById(R.id.show_bottle7);
        showapple = findViewById(R.id.show_bottle6);
        showmixfruit = findViewById(R.id.show_bottle5);
        showlychee = findViewById(R.id.show_bottle4);
        showguava = findViewById(R.id.show_bottle);
        showmango = findViewById(R.id.show_bottle3);

        if(currentOrderRef!=null){
            currentOrderRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer orangeInteger = snapshot.child("Paper Boat Orange").getValue(Integer.class);
                    Integer appleInteger = snapshot.child("Paper Boat Apple").getValue(Integer.class);
                    Integer mixfruitInteger = snapshot.child("Paper Boat Mix Fruit").getValue(Integer.class);
                    Integer lycheeInteger = snapshot.child("Paper Boat Lychee").getValue(Integer.class);
                    Integer guavaInteger = snapshot.child("Paper Boat Guava").getValue(Integer.class);
                    Integer mangoInteger = snapshot.child("Paper Boat Mango").getValue(Integer.class);


                    orange_quantity = orangeInteger != null ? orangeInteger.intValue() : 0;
                    apple_quantity = appleInteger != null ? appleInteger.intValue() : 0;
                    mixfruit_quantity = mixfruitInteger != null ? mixfruitInteger.intValue() : 0;
                    lychee_quantity = lycheeInteger != null ? lycheeInteger.intValue() : 0;
                    guava_quantity = guavaInteger != null ? guavaInteger.intValue() : 0;
                    mango_quantity = mangoInteger != null ? mangoInteger.intValue() : 0;


                    showorange.setText(String.valueOf(orangeInteger != null ? orangeInteger : 0));
                    showapple.setText(String.valueOf(appleInteger != null ? appleInteger : 0));
                    showmixfruit.setText(String.valueOf(mixfruitInteger != null ? mixfruitInteger : 0));
                    showlychee.setText(String.valueOf(lycheeInteger != null ? lycheeInteger : 0));
                    showguava.setText(String.valueOf(guavaInteger != null ? guavaInteger : 0));
                    showmango.setText(String.valueOf(mangoInteger != null ? mangoInteger : 0));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.e(TAG, "onCancelled: " + error.getMessage());
                }
            });
        }



            //profile//
        Button profilebtn = findViewById(R.id.profile_paperboat);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile_intent = new Intent(paperboat.this, profile.class);
                startActivity(profile_intent);
            }
        });
        //profile end//


        int[] imgs = { R.drawable.bagira3, R.drawable.bagira6,R.drawable.bagira7, R.drawable.bagira8,R.drawable.bagira9,R.drawable.bagira10,R.drawable.bagira11};
        flipper = findViewById(R.id.flup);
        flipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bagira_intent = new Intent(paperboat.this, bagira.class);
                startActivity(bagira_intent);            }
        });

        for (int j : imgs) {
            showimage(j);
        }


        //mango
        Button plusmangobutton = findViewById(R.id.plus_bottle_);
        Button minusmangobutton = findViewById(R.id.minus_bottle3);


        plusmangobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mango_quantity==0){
                    addmangoToOrder();
                }
                else {
                    incrementmangoQuantity();
                }
                showmango.setText(String.valueOf(mango_quantity));
            }
        });

        minusmangobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementmangoQuantity();
                showmango.setText(String.valueOf(mango_quantity));
            }
        });



        //guava
        Button plusguavabutton = findViewById(R.id.plus_bottle_3);
        Button minusguavabutton = findViewById(R.id.minus_bottle);


        plusguavabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guava_quantity==0){
                    addguavaToOrder();
                }
                else {
                    incrementguavaQuantity();
                }
                showguava.setText(String.valueOf(guava_quantity));
            }
        });

        minusguavabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementguavaQuantity();
                showguava.setText(String.valueOf(guava_quantity));

            }
        });


        //lychee
        Button pluslycheebutton = findViewById(R.id.plus_bottle_4);
        Button minuslycheebutton = findViewById(R.id.minus_bottle4);


        pluslycheebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lychee_quantity==0){
                    addlycheeToOrder();
                }
                else {
                    incrementlycheeQuantity();
                }
                showlychee.setText(String.valueOf(lychee_quantity));
            }
        });

        minuslycheebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementlycheeQuantity();
                showlychee.setText(String.valueOf(lychee_quantity));

            }
        });

        //mixfruit
        Button plusmixfruitbutton = findViewById(R.id.plus_bottle_5);
        Button minusmixfruitbutton = findViewById(R.id.minus_bottle5);


        plusmixfruitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixfruit_quantity==0){
                    addmixfruitToOrder();
                }
                else {
                    incrementmixfruitQuantity();
                }
                showmixfruit.setText(String.valueOf(mixfruit_quantity));
            }
        });
        minusmixfruitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementmixfruitQuantity();
                showmixfruit.setText(String.valueOf(mixfruit_quantity));

            }
        });


        //apple
        Button plusapplebutton = findViewById(R.id.plus_bottle_6);
        Button minusapplebutton = findViewById(R.id.minus_bottle6);


        plusapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(apple_quantity==0){
                    addappleToOrder();
                }
                else {
                    incrementappleQuantity();
                }
                showapple.setText(String.valueOf(apple_quantity));
            }
        });

        minusapplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementappleQuantity();
                showapple.setText(String.valueOf(apple_quantity));

            }
        });


        //orange
        Button plusorangebutton = findViewById(R.id.plus_bottle_7);
        Button minusorangebutton = findViewById(R.id.minus_bottle7);


        plusorangebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orange_quantity==0){
                    addorrangeToOrder();
                }
                else {
                    incrementorrangeQuantity();
                }
                showorange.setText(String.valueOf(orange_quantity));
            }
        });
        minusorangebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementorangeQuantity();
                showorange.setText(String.valueOf(orange_quantity));
            }
        });

    }

    private void addorrangeToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Orange", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Paper Boat Orange").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        orange_quantity = order.getQuantity();
        showorange.setText(String.valueOf(orange_quantity));
    }

    private void incrementorrangeQuantity() {
        orange_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Orange").setValue(orange_quantity);
        }
    }

    private void decrementorangeQuantity() {
        if (orange_quantity > 0) {
            orange_quantity--;
            if (currentOrderRef != null) {
                if (orange_quantity > 0) {
                    currentOrderRef.child("Paper Boat Orange").setValue(orange_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Orange").removeValue();
                }
            }
        }
    }

    private void decrementappleQuantity() {
        if (apple_quantity > 0) {
            apple_quantity--;
            if (currentOrderRef != null) {
                if (apple_quantity > 0) {
                    currentOrderRef.child("Paper Boat Apple").setValue(apple_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Apple").removeValue();
                }
            }
        }
    }

    private void incrementappleQuantity() {
        apple_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Apple").setValue(apple_quantity);
        }
    }

    private void addappleToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Apple", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Paper Boat Apple").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        apple_quantity = order.getQuantity();
        showapple.setText(String.valueOf(apple_quantity));
    }

    private void addmixfruitToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Mix Fruit", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Paper Boat Mix Fruit").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        mixfruit_quantity = order.getQuantity();
        showmixfruit.setText(String.valueOf(mixfruit_quantity));
    }

    private void incrementmixfruitQuantity() {
        mixfruit_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Mix Fruit").setValue(mixfruit_quantity);
        }
    }

    private void decrementmixfruitQuantity() {
        if (mixfruit_quantity > 0) {
            mixfruit_quantity--;
            if (currentOrderRef != null) {
                if (mixfruit_quantity > 0) {
                    currentOrderRef.child("Paper Boat Mix Fruit").setValue(mixfruit_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Mix Fruit").removeValue();
                }
            }
        }
    }

    private void decrementlycheeQuantity() {
        if (lychee_quantity > 0) {
            lychee_quantity--;
            if (currentOrderRef != null) {
                if (lychee_quantity > 0) {
                    currentOrderRef.child("Paper Boat Lychee").setValue(lychee_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Lychee").removeValue();
                }
            }
        }

    }

    private void incrementlycheeQuantity() {
        lychee_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Lychee").setValue(lychee_quantity);
        }
    }

    private void addlycheeToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Lychee", 1);
        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;
        DatabaseReference newOrderRef = orderRef.child(phoneNumber);
        newOrderRef.child("Paper Boat Lychee").setValue(order.getQuantity());
        currentOrderRef = newOrderRef;
        lychee_quantity = order.getQuantity();
        showlychee.setText(String.valueOf(lychee_quantity));
    }

    private void addguavaToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Guava", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Paper Boat Guava").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        guava_quantity = order.getQuantity();
        showguava.setText(String.valueOf(guava_quantity));
    }

    private void incrementguavaQuantity() {
        guava_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Guava").setValue(guava_quantity);
        }

    }

    private void decrementguavaQuantity() {
        if (guava_quantity > 0) {
            guava_quantity--;
            if (currentOrderRef != null) {
                if (guava_quantity > 0) {
                    currentOrderRef.child("Paper Boat Guava").setValue(guava_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Guava").removeValue();
                }
            }
        }
    }

    private void incrementmangoQuantity() {
        mango_quantity++;
        if (currentOrderRef != null) {
            currentOrderRef.child("Paper Boat Mango").setValue(mango_quantity);
        }
    }

    private void decrementmangoQuantity() {
        if (mango_quantity > 0) {
            mango_quantity--;
            if (currentOrderRef != null) {
                if (mango_quantity > 0) {
                    currentOrderRef.child("Paper Boat Mango").setValue(mango_quantity);
                }
                else {
                    currentOrderRef.child("Paper Boat Mango").removeValue();
                }
            }
        }
    }

    private void addmangoToOrder() {
        paperboat.Order order = new paperboat.Order("Paper Boat Mango", 1);

        DatabaseReference orderNodeRef = orderRef.push();
        String orderId = orderNodeRef.getKey();
        String phoneNumber = phone;

        DatabaseReference newOrderRef = orderRef.child(phoneNumber);

        newOrderRef.child("Paper Boat Mango").setValue(order.getQuantity());

        currentOrderRef = newOrderRef;

        mango_quantity = order.getQuantity();
        showmango.setText(String.valueOf(mango_quantity));
    }

    private void showimage(int img) {
        ImageView imageview=new ImageView(this);
        imageview.setBackgroundResource(img);


        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
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