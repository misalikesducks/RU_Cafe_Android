package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class DonutOrderingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner typeSpinner, flavourSpinner, quantitySpinner;
    Button addToOrderButton, addButton, removeButton;
    ListView donutOrderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_ordering);

        // initialize all components of GUI
        typeSpinner = findViewById(R.id.typeSpinner);
        typeSpinner.setOnItemSelectedListener(this);
        flavourSpinner = findViewById(R.id.flavourSpinner);
        flavourSpinner.setOnItemSelectedListener(this);
        quantitySpinner = findViewById(R.id.quantitySpinner);
        quantitySpinner.setOnItemSelectedListener(this);

        addToOrderButton = findViewById(R.id.addToOrderButton);
        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeOrderButton);

        donutOrderListView = findViewById(R.id.donutOrderListView);

        // set up spinners
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.donut_types_array,
                        android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.quantity_array,
                android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }

    //interface overrides
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { } // can leave it empty

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
