package com.fouedamiri.onamangepourvous;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    DBHandler db = new DBHandler(this) ;
    ListView lst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lst = findViewById(R.id.Data);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                long rowId = id;

                // get the data item for the clicked row
                String[] row = (String[]) adapterView.getItemAtPosition(position);


                // create an intent to launch the DetailsActivity
                Intent intent = new Intent(Activity3.this, DetailsActivity.class);

                // add the row data as extras to the intent
                intent.putExtra("rowId", rowId);
                intent.putExtra("id", row[0]);
                intent.putExtra("name", row[1]);
                intent.putExtra("address", row[2]);
                intent.putExtra("service", row[3]);
                intent.putExtra("food", row[4]);
                intent.putExtra("price", row[5]);
                intent.putExtra("stars", row[6]);

                // launch the DetailsActivity
                startActivity(intent);
            }
        });
        ShowData();
    }
    public void ShowData (){
        ArrayList listData =db.getAllrecord();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);
    }
}