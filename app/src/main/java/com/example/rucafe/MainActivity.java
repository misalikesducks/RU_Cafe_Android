package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button; 
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button coffeeButton, donutButton, currentOrderButton, storeOrderButton;
    protected static Order currOrder = new Order();
    protected static StoreOrders currStoreOrder = new StoreOrders();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffeeButton = (Button) findViewById(R.id.coffeeButton);
        donutButton = (Button) findViewById(R.id.donutButton);
        currentOrderButton = (Button) findViewById(R.id.currentOrderButton);
        storeOrderButton = (Button) findViewById(R.id.storeOrderButton);
    }

    public void loadCoffee(View view){
        Intent intent = new Intent(MainActivity.this, CoffeeOrderingActivity.class);
        startActivity(intent);
    }

    public void loadDonut(View view){
        Intent intent = new Intent(MainActivity.this, DonutOrderingActivity.class);
        startActivity(intent);
    }

    public void loadStoreOrder(View view){
        Intent intent = new Intent(MainActivity.this, OrderDetailActivity.class);
        startActivity(intent);
    }

    public void loadCurrentOrder(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
        startActivity(intent);
    }
}