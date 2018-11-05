package edu.gatech.donatrix.controllers;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.ItemDao;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.Item;
import edu.gatech.donatrix.model.Location;

public class ItemListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Location location;
    private Intent intent;
    private int locationId;

    private Spinner itemSpinner;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        intent = getIntent();
        locationId = intent.getIntExtra("location_id", 0);
        Log.d("Zeke", "" + locationId);

        if (locationId == 0) {
            Toast toast = Toast.makeText(this, "Location not passed in", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        } else {
            location = LocationDao.getLocationByID(locationId, this);
            Log.d("Zeke", location.getName());
        }

        itemSpinner = (Spinner) findViewById(R.id.itemListItemSpinner);
        itemSpinner.setOnItemSelectedListener(this);

        try {
            Log.d("Zeke5", location.getName());
            ArrayAdapter<Item> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ItemDao.getItemsFromLocation(location, this).toArray());
            itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(itemArrayAdapter);
        } catch (Exception e) {
            Log.d("Zeke2", e.getMessage());
            Toast toast = Toast.makeText(this, "There are no items at this Location", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            Item[] empty = new Item[0];
            ArrayAdapter<Item> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, empty);
            itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(itemArrayAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = (Item) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        item = null;
    }

    public void onCancelButtonPressed(View view) {
        finish();
    }

    public void onDetailsButtonPressed(View view) {
        Intent intent = new Intent(ItemListActivity.this, ItemDetailActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

}
