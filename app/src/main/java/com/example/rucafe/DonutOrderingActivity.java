package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * DonutOrderingActivity is the Activity class for the layout file activity_donut_ordering.xml
 * @author Connie Chen, Tiffany Lee
 */
public class DonutOrderingActivity extends AppCompatActivity{
    Spinner typeSpinner, flavourSpinner, quantitySpinner;
    Button addToOrderButton, addButton, removeButton;
    ListView donutOrderListView;
    TextView subtotalNumTextView;

    protected Order currDonutOrder = new Order();

    /**
     * Initializes all the components of the layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_ordering);

        // initialize all components of GUI
        typeSpinner = findViewById(R.id.typeSpinner);
        flavourSpinner = findViewById(R.id.flavourSpinner);
        quantitySpinner = findViewById(R.id.quantitySpinner);

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

    // HELPER METHODS

    /**
     * Initializes the Listener for typeSpinner and sets the values for flavourSpinner
     * @param s is the Spinner to be initiliazed
     */
    public void setUpFlavourSpinner(Spinner s){
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Reacts when user selects an item in the Spinner s, updates the items in flavoursSpinner
             * @param parent
             * @param view
             * @param position
             * @param id
             */
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
             /**
             * Does nothing when an item is not selected in the Spinner s
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Sets up the Listener for the donutOrderListView
     * @param l is the ListView to be initiliazed
     */
    public void setUpListView(ListView l) {
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Reacts when user clicks an item in the ListView l
             * Prompts user for the removal of an item from the Order
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder removeAlert = new AlertDialog.Builder(DonutOrderingActivity.this);
                removeAlert.setMessage("Remove the selected item?").setTitle("Remove");
                removeAlert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Donut selectedDonut = (Donut)donutOrderListView.getItemAtPosition(position);

                        if(currDonutOrder.remove(selectedDonut)){
                            ArrayAdapter<Donut> adapter = new ArrayAdapter(
                                    DonutOrderingActivity.this, R.layout.currorder_listitem,
                                    R.id.displayTextView, currDonutOrder.getItems());
                            donutOrderListView.setAdapter(adapter);
                            currDonutOrder.setSubTotal();
                            displaySubtotal();
                            Toast toast = Toast.makeText(DonutOrderingActivity.this,
                                    "Item Removed", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                removeAlert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(DonutOrderingActivity.this,
                                "Remove Cancelled", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                AlertDialog removeDialog = removeAlert.create();
                removeDialog.show();
            }
        });
    }

    /**
     * Adds donut(s) to the order, updating the subtotalTextView and donutOrderListView
     * @param view
     */
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

    /**
     * Adds the Donut order to the current Order
     * @param view
     */
    public void addToOrder(View view){
        if(currDonutOrder.items.isEmpty()){ // checks if order is empty, shows error
            Toast toast = Toast.makeText(DonutOrderingActivity.this,
                    "Error: No Items in Order", Toast.LENGTH_SHORT);
            toast.show();
        }else{ // add order to current order
            for(MenuItem item : currDonutOrder.getItems()){
                MainActivity.currOrder.getItems().add(item);
            }

            // clears listView and textView
            subtotalNumTextView.setText(" ");
            donutOrderListView.setAdapter(null);

            currDonutOrder = new Order();

            Toast toast = Toast.makeText(DonutOrderingActivity.this,
                    "Order Placed", Toast.LENGTH_SHORT);
            toast.show();
        }
        finish();
    }

    /**
     * Updates the subtotalNumTextView with the subtotal of the donutOrder
     */
    public void displaySubtotal(){
        subtotalNumTextView.setText("" + StoreOrders.convertToMoney(currDonutOrder.getSubTotal()));
    }
}
