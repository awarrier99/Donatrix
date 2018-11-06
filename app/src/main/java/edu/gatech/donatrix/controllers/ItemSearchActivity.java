package edu.gatech.donatrix.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.model.ItemCategory;
import edu.gatech.donatrix.model.Location;

public class ItemSearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner locationSpinner;
    private Spinner categorySpinner;
    private Spinner resultSpinner;

    private Location location;
    private ItemCategory category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        locationSpinner = (Spinner) findViewById(R.id.itemSearchLocationSpinner);
        categorySpinner = (Spinner) findViewById(R.id.itemSearchCategorySpinner);
        locationSpinner.setOnItemSelectedListener(this);
        categorySpinner.setOnItemSelectedListener(this);

        Log.d("Donatrix", "Hello");
        resultSpinner = (Spinner) findViewById(R.id.itemSearchResultSpinner);
        resultSpinner.setOnItemSelectedListener(this);
        List<Location> locList = LocationDao.getLocations(this);
        locList.add(0, null);
        ArrayAdapter<Location> locationArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locList.toArray());
        locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationArrayAdapter);

        Log.d("Donatrix", "Hello2");
        ItemCategory[] itemCategoryArray = ItemCategory.values();
        List<String> categories = new ArrayList<>();
        for (ItemCategory i: itemCategoryArray) {
            categories.add(i.getCategory());
        }
        categories.add(0, "");
        ArrayAdapter<String> itemCategoryArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories.toArray());
        Log.d("Donatrix", "Hello3");
        itemCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d("Donatrix", "Hello5");
        categorySpinner.setAdapter(itemCategoryArrayAdapter);
        Log.d("Donatrix", "Hello4");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.itemSearchLocationSpinner) {
            location = (Location) spinner.getItemAtPosition(position);
        } else if (spinner.getId() == R.id.itemSearchCategorySpinner) {
            category = (ItemCategory) spinner.getItemAtPosition(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        category = null;
        location = null;
    }

    public void onCancelButtonPressed(View view) {
        finish();
    }

    public void onSearchButtonPressed(View view) {
        String[] result;
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, result);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resultSpinner.setAdapter(stringArrayAdapter);
    }
}
