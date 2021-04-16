package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

public class DonutOrderingActivity extends AppCompatActivity {
    Spinner typeSpinner, quantitySpinner;
    Button addToOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_ordering);
        
        
    }
    
    
    /*
        Donut
        add donut
        - no type selected
        - no amount selected
        - no flavour selected
        remove donut
        - order is empty
        - no donut selected
        - donut is removed
        add to order
        - order is empty
        - donut is added
    */
}