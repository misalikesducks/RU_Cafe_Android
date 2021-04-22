package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Store order activity class for Store Order page
 * Implements viewing different orders placed and removing orders
 * @author Connie Chen, Tiffany Lee
 */
public class StoreOrderActivity extends AppCompatActivity {
    Spinner orderIDSpinner;
    TextView storeOrderPriceTextView;
    ListView storeOrdersListView;
    Button cancelOrderBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);

        orderIDSpinner = findViewById(R.id.orderIDSpinner);
        storeOrderPriceTextView = findViewById(R.id.storeOrderPriceTextView);
        storeOrdersListView = findViewById(R.id.storeOrdersListView);
        cancelOrderBtn = findViewById(R.id.cancelOrderBtn);

        setUpOrderIDSpinner(orderIDSpinner);
    }

    /**
    * Initializes the orderIDSpinner with an ArrayList of all the Order(s) in currStoreOrder
    * @param s Spinner to be initialized
    */
    public void setUpOrderIDSpinner(Spinner s){
        ArrayList<String> orderIDs = new ArrayList<>();
        for(Order order : MainActivity.currStoreOrder.getOrders()){
            orderIDs.add("" + order.getOrderID());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, orderIDs);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int orderIDToDisplay = Integer.parseInt(parent.getItemAtPosition(position).toString());
                Order orderToDisplay = MainActivity.currStoreOrder.findOrder(orderIDToDisplay);

                ArrayList<MenuItem> itemsToDisplay = new ArrayList<>();
                for(MenuItem item : orderToDisplay.getItems()){
                    itemsToDisplay.add(item);
                }

                ArrayAdapter adapter = new ArrayAdapter(StoreOrderActivity.this,
                        R.layout.currorder_listitem, R.id.displayTextView, itemsToDisplay);
                storeOrdersListView.setAdapter(adapter);

                storeOrderPriceTextView.setText(StoreOrders.convertToMoney(orderToDisplay.getTotal()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Event handler for cancelOrderBtn
     * Removes a selected Order from the StoreOrder object ArrayList orders
     * @param view
     */
    public void cancelOrder(View view){
        if(MainActivity.currStoreOrder.getOrders().size() == 0){ // no orders
            Toast toast = Toast.makeText(StoreOrderActivity.this,
                    "No orders to remove", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            // get value from spinner + remove it
            int orderToRemoveID = Integer.parseInt(orderIDSpinner.getSelectedItem().toString());
            Order orderToRemove = MainActivity.currStoreOrder.findOrder(orderToRemoveID);

            if(MainActivity.currStoreOrder.getOrders().remove(orderToRemove)){
                setUpOrderIDSpinner(orderIDSpinner);

                if(MainActivity.currStoreOrder.getOrders().size() == 0){
                    storeOrdersListView.setAdapter(null);
                    storeOrderPriceTextView.setText("$0.00");
                }

                Toast toast = Toast.makeText(StoreOrderActivity.this,
                        "Order cancelled", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }    
}