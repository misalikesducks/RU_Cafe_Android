package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

/**
 * Activity class for the Order Details page
 * Implements removing MenuItems and placing an Order to the currStoreOrder
 * @author Connie Chen, Tiffany Lee
 */
public class OrderDetailActivity extends AppCompatActivity{
    ListView ordersListview;
    TextView subtotalTextView, taxTextView, totalTextView;

    protected ArrayList<MenuItem> itemsToDisplay = new ArrayList<>();
    protected ArrayAdapter<MenuItem> adapter;
    protected MenuItem selectedItem;
    public static final int EMPTY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        subtotalTextView = (TextView) findViewById(R.id.subtotalDisplayTextView);
        taxTextView = (TextView) findViewById(R.id.salesTaxDisplayTextView);
        totalTextView = (TextView) findViewById(R.id.totalDisplayTextView);
        ordersListview = (ListView) findViewById(R.id.orderDisplayListView);
        populateListView();
        ordersListview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ordersListview.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectedItem = itemsToDisplay.get(position);
                view.setSelected(true);
            }
        });
    }

    /**
     * Populates the adapter with menuItems and set it to ListView
     */
    public void populateListView(){
        for(int i = 0; i < MainActivity.currOrder.items.size(); i++){
            itemsToDisplay.add(MainActivity.currOrder.items.get(i));
        }
        adapter = new ArrayAdapter
                (this, R.layout.currorder_listitem,R.id.displayTextView,itemsToDisplay);
        ordersListview.setAdapter(adapter);

        MainActivity.currOrder.setSubTotal();
        subtotalTextView.setText(StoreOrders.convertToMoney(MainActivity.currOrder.getSubTotal()));
        MainActivity.currOrder.setSalesTax();
        taxTextView.setText(StoreOrders.convertToMoney(MainActivity.currOrder.getSalesTax()));
        MainActivity.currOrder.setTotal();
        totalTextView.setText(StoreOrders.convertToMoney(MainActivity.currOrder.getTotal()));
    }

    /**
     * Place an Order to currStoreOrder
     * @param view - click of placeOrderButton
     */
    public void placeOrder(View view){
        if(MainActivity.currOrder.getItems().size() != EMPTY && MainActivity.currStoreOrder.getOrders().add(MainActivity.currOrder)){
            Order.incrementIDNumber();
            MainActivity.currOrder = new Order();
            Toast.makeText(OrderDetailActivity.this, "Order Placed",Toast.LENGTH_SHORT).show();
            itemsToDisplay.clear();
            populateListView();
        }else{
            Toast.makeText(OrderDetailActivity.this, "Could not place an empty order",Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    /**
     * Removes selected Order in ListView
     * @param view - click of removeOrderButton
     */
    public void removeOrder(View view){
        if(selectedItem == null)
            Toast.makeText(OrderDetailActivity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
        else if(itemsToDisplay.size() == EMPTY){
            Toast.makeText(OrderDetailActivity.this, "Could not remove on empty order",Toast.LENGTH_SHORT).show();
        }else if(MainActivity.currOrder.getItems().remove(selectedItem)){
            itemsToDisplay.remove(selectedItem);
            selectedItem = null;
            itemsToDisplay.clear();
            populateListView();
            Toast.makeText(OrderDetailActivity.this, "Item removed",Toast.LENGTH_SHORT).show();
        }
    }
}