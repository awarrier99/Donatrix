package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import edu.gatech.donatrix.R;

/**
 * An Activity for Location Employees
 */
public class LocationEmployeeHomeActivity extends AppCompatActivity {

    private int locationId;

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_location_employee);

        Intent intent = getIntent();
        locationId = intent.getIntExtra("location_id", 0);
    }

    /**
     * What happens when logout is pressed
     * @param view XML
     */
    public void onLogoutPressed(View view) {
        finish();
    }

    /**
     * What happens when add item is pressed
     * @param view XML
     */
    public void onAddItemPressed(View view) {
        Intent intent = new Intent(LocationEmployeeHomeActivity.this, AddItemActivity.class);
        intent.putExtra("location_id", locationId);
        startActivity(intent);
    }

    /**
     * What happens when show items is pressed
     * @param view XML
     */
    public void onShowItemsButtonPressed(View view) {
        Intent intent = new Intent(LocationEmployeeHomeActivity.this, ItemListActivity.class);
        intent.putExtra("location_id", locationId);
        intent.putExtra("all_items", false);
        startActivity(intent);
    }

    /**
     * What happens when show all items is pressed
     * @param view XML
     */
    public void onShowAllItemsPressed(View view) {
        Intent intent = new Intent(LocationEmployeeHomeActivity.this, ItemListActivity.class);
        intent.putExtra("location_id", locationId);
        intent.putExtra("all_items", true);
        startActivity(intent);
    }

    /**
     * What happens when search items is pressed
     * @param view XML
     */
    public void onSearchItemsPressed(View view) {
        try {
            Intent intent = new Intent(this, ItemSearchActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Donatrix", e.getMessage());
        }
    }

    /**
     * What happens when location list is pressed
     * @param view XML
     */
    public void onLocationListPressed(View view) {
        Intent intent = new Intent(this, LocationListActivity.class);
        startActivity(intent);
    }
}
