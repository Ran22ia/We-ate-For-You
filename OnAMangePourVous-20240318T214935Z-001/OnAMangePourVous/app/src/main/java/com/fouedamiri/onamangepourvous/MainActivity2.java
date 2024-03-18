package com.fouedamiri.onamangepourvous;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;



@SuppressWarnings("ALL")
public class MainActivity2 extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String nom = getIntent().getStringExtra("editTextLastName");
        String prenom = getIntent().getStringExtra("editTextFirstName");


        // Display the form data on the screen
        TextView nameTextView = findViewById(R.id.textViewWelcome);
        nameTextView.setText("Bienvenue " + nom + " " + prenom + "!");

        DBHandler dbHandler = new DBHandler(this); // initialize the dbHandler object
        Button button = findViewById(R.id.valider);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingBar ratingFood = findViewById(R.id.ratingFood);
                RatingBar ratingService = findViewById(R.id.ratingService);
                String service;
                String food;
                switch (ratingService.getProgress()) {
                    case 1:
                        service = "Médiocre";
                        break;
                    case 2:
                        service = "Moyen";
                        break;
                    case 3:
                        service = "Bon";
                        break;
                    case 4:
                        service = "Excellent";
                        break;
                    default:
                        service = "unknown";
                        break;
                }
                switch (ratingFood.getProgress()) {
                    case 1:
                        food = "Médiocres";
                        break;
                    case 2:
                        food = "Moyens";
                        break;
                    case 3:
                        food = "Bons";
                        break;
                    case 4:
                        food = "Excellents";
                        break;
                    default:
                        food = "unknown";
                        break;
                }
                EditText editTextPrice = findViewById(R.id.editTextAveragePrice);
                float price = Float.parseFloat(editTextPrice.getText().toString());
                RatingBar ratingBarStars = findViewById(R.id.ratingBar);
                int stars = (int) ratingBarStars.getRating();

                // Save the data to the database using the DbHandler object
                EditText editTextAddress = findViewById(R.id.adress);
                EditText editTextName = findViewById(R.id.RestaurantName);
                String address = editTextAddress.getText().toString();
                String name = editTextName.getText().toString();
                dbHandler.addEvaluation(name, address, service, food, price, stars); // pass the evaluation instance to the addEvaluation method

                // Show a Toast message to confirm the data is saved
                String message = "Evaluation ajoutée:\nNom et adresse:" + name + " ," + address + "\nQualité du service: " + service +
                        "\nQualité des plats: " + food +
                        "\nPrix moyen: " + price +
                        "\nNombre d'étoiles: " + stars;
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_LONG).show();
                editTextPrice.setText("");
                editTextAddress.setText("");
                editTextName.setText("");
            }
        });



        Button buttonsee = findViewById(R.id.See);
        buttonsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, Activity3.class);
                startActivity(intent);
            }
        });


    }}