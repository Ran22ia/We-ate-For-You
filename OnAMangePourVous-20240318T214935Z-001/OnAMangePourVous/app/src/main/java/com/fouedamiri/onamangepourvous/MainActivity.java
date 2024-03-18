package com.fouedamiri.onamangepourvous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nom = findViewById(R.id.editTextFirstName);
        EditText prenom = findViewById(R.id.editTextLastName);
        Button button = findViewById(R.id.buttonSubmit);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("editTextLastName", nom.getText().toString());
                intent.putExtra("editTextFirstName", prenom.getText().toString());
                startActivity(intent);
            }
        });
    }





}