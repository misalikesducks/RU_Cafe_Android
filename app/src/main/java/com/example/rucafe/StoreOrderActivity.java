package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StoreOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
    }
    
    /*
        StoreOrder
        cancel order
        - no store orders
        - order not selected
        - order has been cancelled
        export order
        - successful export
        - unsuccessful export
        - no store orders to export
    */
}