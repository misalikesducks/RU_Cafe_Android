package com.example.rucafe;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    Activity context;
    ArrayList<MenuItem> itemsInCurrentOrder;
    private static LayoutInflater inflater = null;

    public MenuItemAdapter(Activity context, ArrayList<MenuItem> itemsInCurrentOrder){
        super(context, 0, itemsInCurrentOrder);
        this.context = context;
        this.itemsInCurrentOrder = itemsInCurrentOrder;
    }

    @Override
    public int getCount(){
        return itemsInCurrentOrder.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return itemsInCurrentOrder.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null)
            itemView = LayoutInflater.from(context).inflate(R.layout.activity_current_order, parent,false);
        MenuItem currMenuItem = itemsInCurrentOrder.get(position);
        return itemView;
    }
}
