package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.*;

public class CoffeeOrderingActivity extends AppCompatActivity {
    Spinner sizesSpinner, quantitySpinner;
    Button addToOrderButton;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_ordering);
        sizesSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.coffee_sizes_array,
                                                android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizesSpinner.setAdapter(adapter);

        quantitySpinner = (Spinner)findViewById(R.id.amountSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.quantity_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);

        addToOrderButton =  (Button)findViewById(R.id.addToOrderButton);
    }


    /*
        Coffee
        get add ins
        - no size chosen
        - no quantity chosen
        select quantity
        - no size chosen
        add coffee to order
        - no size chosen
        - no quantity chosen
        - added to order
    */
}