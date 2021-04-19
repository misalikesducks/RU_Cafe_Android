package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class CurrentOrderActivity extends AppCompatActivity {
    ListView ordersListview;
    Button removeOrderButton, placeOrderButton;
    TextView subtotalTextView, taxTextView, totalTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ordersListview = (ListView) findViewById(R.id.ordersListView);

        removeOrderButton = (Button) findViewById(R.id.removeButton);
        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);

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