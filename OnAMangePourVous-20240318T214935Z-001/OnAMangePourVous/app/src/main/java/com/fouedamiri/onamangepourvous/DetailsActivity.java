package com.fouedamiri.onamangepourvous;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    DBHandler db;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        Intent intent = getIntent();
        long rowId = getIntent().getLongExtra("rowId", -1);
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String service = intent.getStringExtra("service");
        String food = intent.getStringExtra("food");
        String price = intent.getStringExtra("price");
        String stars = intent.getStringExtra("stars");

        // Get references to each TextView in the layout
        TextView nameTextView = findViewById(R.id.name_textview);
        TextView addressTextView = findViewById(R.id.address_textview);
        TextView serviceTextView = findViewById(R.id.service_textview);
        TextView foodTextView = findViewById(R.id.food_textview);
        TextView priceTextView = findViewById(R.id.price_textview);
        TextView starsTextView = findViewById(R.id.stars_textview);

// Set the text of each TextView to the appropriate variable
        nameTextView.setText(name);
        addressTextView.setText(address);
        serviceTextView.setText(service);
        foodTextView.setText(food);
        priceTextView.setText(String.valueOf(price));
        starsTextView.setText(String.valueOf(stars));


        Button edit = findViewById(R.id.editButton);
        Button delete = findViewById(R.id.deleteButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateData(name, address,service, food, price, stars);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRecord((int) rowId);
                finish();

            }
        });
    }
    }
