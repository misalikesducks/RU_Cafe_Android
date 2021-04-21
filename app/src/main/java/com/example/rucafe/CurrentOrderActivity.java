package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrderActivity extends AppCompatActivity{
    ListView ordersListview;
    Button removeOrderButton, placeOrderButton;
    TextView subtotalTextView, taxTextView, totalTextView;

    protected ArrayList<MenuItem> itemsToDisplay = new ArrayList<>();
    protected ArrayAdapter<MenuItem> adapter;
    protected MenuItem selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

//        ordersListview.setOnClickListener(this);
        ordersListview = (ListView) findViewById(R.id.orderDisplayListView);
        populateListView();
        ordersListview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ordersListview.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectedItem = itemsToDisplay.get(position);
                Toast.makeText(CurrentOrderActivity.this, selectedItem.toString(),Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });
        removeOrderButton = (Button) findViewById(R.id.removeOrderButton);
        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        subtotalTextView = (TextView) findViewById(R.id.subtotalDisplayTextView);
        taxTextView = (TextView) findViewById(R.id.salesTaxDisplayTextView);
        totalTextView = (TextView) findViewById(R.id.totalDisplayTextView);
    }

    public void populateListView(){
        for(int i = 0; i < MainActivity.currOrder.items.size(); i++){
            itemsToDisplay.add(MainActivity.currOrder.items.get(i));
        }
        adapter = new ArrayAdapter
                (this, R.layout.currorder_listitem,R.id.displayTextView,itemsToDisplay);
        ordersListview.setAdapter(adapter);

    }




    // public void populateListView()
    
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