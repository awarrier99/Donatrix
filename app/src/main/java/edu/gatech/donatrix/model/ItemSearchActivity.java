package edu.gatech.donatrix.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.Database;
import edu.gatech.donatrix.dao.LocationDao;

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

        resultSpinner = (Spinner) findViewById(R.id.itemSearchResultSpinner);
        resultSpinner.setOnItemSelectedListener(this);

        List<Location> locList = LocationDao.getLocations(this);
        locList.add(0, null);
        ArrayAdapter<Location> locationArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locList.toArray());
        locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationArrayAdapter);

        ItemCategory[] itemCategoryArray = ItemCategory.values();
        List<ItemCategory> itemCategories = Arrays.asList(itemCategoryArray);
        itemCategories.add(0, null);
        ArrayAdapter<ItemCategory> itemCategoryArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itemCategories.toArray());
        itemCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(itemCategoryArrayAdapter);
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
