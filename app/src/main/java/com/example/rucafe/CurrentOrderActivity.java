package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {
    ListView ordersListview;
    Button removeOrderButton, placeOrderButton;
    TextView subtotalTextView, taxTextView, totalTextView;

    ArrayList<MenuItem> itemsToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        //Initializing ListView with Orders
        ordersListview = (ListView) findViewById(R.id.ordersListView);
        for(int i = 0; i < MainActivity.currOrder.items.size(); i++){
            itemsToDisplay.add(MainActivity.currOrder.items.get(i));
        }

        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_1, itemsToDisplay);
        ordersListview.setAdapter(adapter);
        removeOrderButton = (Button) findViewById(R.id.removeOrderButton);
        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        subtotalTextView = (TextView) findViewById(R.id.subtotalDisplayTextView);
        taxTextView = (TextView) findViewById(R.id.salesTaxDisplayTextView);
        totalTextView = (TextView) findViewById(R.id.totalDisplayTextView);
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