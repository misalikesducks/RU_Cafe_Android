package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.*;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CoffeeOrderingActivity extends AppCompatActivity{
    Spinner sizesSpinner, quantitySpinner;
    Button addToOrderButton;
    TextView coffeeTotalTextview;
    Switch caramelSwitch, creamSwitch, whippedSwitch, milkSwitch, syrupSwitch;

    protected Order currCoffeeOrder = MainActivity.currOrder;
    protected ArrayList<String> adds = new ArrayList<>();
    protected Coffee currCoffee = new Coffee(0.0, null, 1, adds);

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
        sizesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sizeText = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), sizeText, Toast.LENGTH_SHORT).show();
                currCoffee.setSize(sizeText);
                currCoffee.setPrice(currCoffee.itemPrice());
                System.out.println(currCoffee.getPrice());
                coffeeTotalTextview.setText(""+StoreOrders.convertToMoney(currCoffee.getPrice()));
                //Toast.makeText(parent.getContext(), "Price: "+StoreOrders.convertToMoney(currCoffee.getPrice()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        quantitySpinner = (Spinner)findViewById(R.id.amountSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.quantity_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currCoffee.setQuantity(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                currCoffee.setPrice(currCoffee.itemPrice());
                coffeeTotalTextview.setText(""+StoreOrders.convertToMoney(currCoffee.getPrice()));
                Toast.makeText(parent.getContext(),
                        "Price: " +StoreOrders.convertToMoney(currCoffee.getPrice()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addToOrderButton = (Button) findViewById(R.id.addToOrderButton);

        coffeeTotalTextview = (TextView)findViewById(R.id.totalTextView);

        creamSwitch = (Switch)findViewById(R.id.creamSwitch);
        milkSwitch = (Switch) findViewById(R.id.milkSwitch);
        syrupSwitch = (Switch) findViewById(R.id.syrupSwitch);
        whippedSwitch = (Switch) findViewById(R.id.whippedSwitch);
        caramelSwitch = (Switch) findViewById(R.id.caramelSwitch);

    }

    public void getAddIns(View view){
        adds = currCoffee.addIns;
        if(caramelSwitch.isChecked() && !adds.contains(caramelSwitch.getText()))
            currCoffee.add(caramelSwitch.getText());
        else if(!caramelSwitch.isChecked())
            currCoffee.remove(caramelSwitch.getText());
        if(creamSwitch.isChecked() && !adds.contains(creamSwitch.getText()))
            currCoffee.add(creamSwitch.getText());
        else if(!creamSwitch.isChecked())
            currCoffee.remove(creamSwitch.getText());
        if(milkSwitch.isChecked() && !adds.contains(milkSwitch.getText()))
            currCoffee.add(milkSwitch.getText());
        else if(!milkSwitch.isChecked())
            currCoffee.remove(milkSwitch.getText());
        if(syrupSwitch.isChecked() && !adds.contains(syrupSwitch.getText()))
            currCoffee.add(syrupSwitch.getText());
        else if(!syrupSwitch.isChecked())
            currCoffee.remove(syrupSwitch.getText());
        if(whippedSwitch.isChecked() && !adds.contains(whippedSwitch.getText()))
            currCoffee.add(whippedSwitch.getText());
        else if(!whippedSwitch.isChecked())
            currCoffee.remove(whippedSwitch.getText());
        currCoffee.setPrice(currCoffee.itemPrice());
        coffeeTotalTextview.setText(""+StoreOrders.convertToMoney(currCoffee.getPrice()));

    }

    public void addCoffeeToOrder(View view){
        if(currCoffeeOrder.getItems().add(currCoffee)) {
            currCoffeeOrder.setSubTotal();
        }
    }

    /*

         error check
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