package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class DonutOrderingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner typeSpinner, flavourSpinner, quantitySpinner;
    Button addToOrderButton, addButton, removeButton;
    ListView donutOrderListView;
    TextView subtotalNumTextView;

    protected Order currDonutOrder = new Order();

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
        setUpListView(donutOrderListView);

        subtotalNumTextView = findViewById(R.id.subtotalNumTextView);

        // set up spinners
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.donut_types_array,
                        android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        setUpFlavourSpinner(typeSpinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.quantity_array,
                android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }

    // interface overrides
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        displaySubtotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { } // can leave it empty

    // helper methods
    public void setUpFlavourSpinner(Spinner s){
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = parent.getItemAtPosition(position).toString();

                if(type.equals("Yeast")){
                    ArrayAdapter<CharSequence> adapter =
                            ArrayAdapter.createFromResource(getApplicationContext(),
                                    R.array.yeast_flavours_array,
                                    android.R.layout.simple_spinner_dropdown_item);
                    flavourSpinner.setAdapter(adapter);
                } else if(type.equals("Cake")){
                    ArrayAdapter<CharSequence> adapter =
                            ArrayAdapter.createFromResource(getApplicationContext(),
                                    R.array.cake_flavours_array,
                                    android.R.layout.simple_spinner_dropdown_item);
                    flavourSpinner.setAdapter(adapter);
                } else if(type.equals("Donut Hole")){
                    ArrayAdapter<CharSequence> adapter =
                            ArrayAdapter.createFromResource(getApplicationContext(),
                                    R.array.donut_hole_flavours_array,
                                    android.R.layout.simple_spinner_dropdown_item);
                    flavourSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void setUpListView(ListView l) {
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder removeAlert = new AlertDialog.Builder(DonutOrderingActivity.this);
                removeAlert.setMessage("Remove the selected item?").setTitle("Remove");
                removeAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                removeAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog removeDialog = removeAlert.create();
                removeDialog.show();
                // https://stackoverflow.com/questions/4834750/how-to-get-the-selected-item-from-listview
            }
        });
    }

    public void addDonut(View view){
        // create a new Donut
        String donutType = typeSpinner.getSelectedItem().toString();
        String donutFlavour = flavourSpinner.getSelectedItem().toString();
        int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
        Donut donut = new Donut(donutType, donutFlavour, quantity);
        donut.setPrice(donut.itemPrice());

        // add Donut to currentOrder + update subtotal
        if(currDonutOrder.add(donut)){
            currDonutOrder.setSubTotal();

            // update listview
            ArrayAdapter<Donut> adapter = new ArrayAdapter(this, R.layout.currorder_listitem,
                    R.id.displayTextView, currDonutOrder.getItems());
            donutOrderListView.setAdapter(adapter);

            displaySubtotal();
        }
    }

    public void displaySubtotal(){
        subtotalNumTextView.setText("" + StoreOrders.convertToMoney(currDonutOrder.getSubTotal()));
    }

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
