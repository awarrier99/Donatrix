package edu.gatech.donatrix.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.data.RESTCaller;

public class ItemListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Intent intent;
    private int locationId;

    private Spinner itemSpinner;
    private String item;
    private List<Map<String, Object>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        intent = getIntent();
        locationId = intent.getIntExtra("location_id", 0);

        if (locationId == 0) {
            Toast toast = Toast.makeText(this, "Location not passed in", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
        }

        itemSpinner = (Spinner) findViewById(R.id.itemListItemSpinner);
        itemSpinner.setOnItemSelectedListener(this);

        try {
            Map<String, Object> body = new HashMap<>();
            body.put("loc_id", locationId);

            Map<String, Object> response = RESTCaller.post("https://donatrix-api.herokuapp.com/location/getItems", body);
            boolean success = (boolean) response.get("success");

            if (success) {
                List<Map<String, Object>> fullItems = (ArrayList<Map<String, Object>>) response.get("items");
                this.items = fullItems;
                List<String> items = new ArrayList<>();
                for (Map m: fullItems) {
                    items.add((String) m.get("s_description"));
                }
                ArrayAdapter<String> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);
                itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                itemSpinner.setAdapter(itemArrayAdapter);
            } else {
                Toast toast = Toast.makeText(this, "There are no items at this Location", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                String[] empty = new String[0];
                ArrayAdapter<String> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, empty);
                itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                itemSpinner.setAdapter(itemArrayAdapter);
            }
        } catch (Exception e) {
            Log.d("Zeke2", e.getMessage());
            Toast toast = Toast.makeText(this, "There are no items at this Location", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            String[] empty = new String[0];
            ArrayAdapter<String> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, empty);
            itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(itemArrayAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        item = null;
    }

    public void onCancelButtonPressed(View view) {
        finish();
    }

    public void onDetailsButtonPressed(View view) {
        Map<String, Object> item = new LinkedHashMap<>();
        for (Map m: items) {
            if (((String) m.get("s_description")).equals(this.item)) {
                item = m;
            }
        }
        Intent intent = new Intent(ItemListActivity.this, ItemDetailActivity.class);
        intent.putExtra("item", (Serializable) item);
        startActivity(intent);
    }

}
