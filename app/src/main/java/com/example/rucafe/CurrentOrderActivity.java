package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CurrentOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
    }
    
    /*
        CurrentOrder
        remove
            - order is empty
            - item not selected
        place order
            - order is emtpy
            - order has been placed
    */
}