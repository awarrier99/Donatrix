package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.ItemDao;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.Item;
import edu.gatech.donatrix.model.ItemCategory;
import edu.gatech.donatrix.model.LocationEmployee;

public class AddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText sDescriptionField;
    private EditText fDescriptionField;
    private EditText valueField;
    private EditText commentsField;
    private Spinner itemCategorySpinner;

    private ItemCategory itemCategory;
    private int locationId;

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        intent = getIntent();
        locationId = intent.getIntExtra("location_id", 0);

        sDescriptionField = (EditText) findViewById(R.id.addItemSDescription);
        fDescriptionField = (EditText) findViewById(R.id.addItemFDescription);
        valueField = (EditText) findViewById(R.id.addItemValue);
        commentsField = (EditText) findViewById(R.id.addItemComments);
        itemCategorySpinner = (Spinner) findViewById(R.id.addItemCategory);
        itemCategorySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<ItemCategory>(this, android.R.layout.simple_spinner_item,
                ItemCategory.values()){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemCategorySpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemCategory = (ItemCategory) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    public void onAddItemPressed(View view) {
        Log.d("Donatrix", Integer.valueOf(locationId).toString());
        Map<String, Object> body = new HashMap<>();
        body.put("sDesc","" + sDescriptionField.getText());
        body.put("fDesc", "" + fDescriptionField.getText());
        body.put("value", Double.parseDouble(valueField.getText().toString()));
        body.put("cat", itemCategory.getCategory());
        body.put("comments", "" + commentsField.getText());
        body.put("loc_id", locationId);

        Map<String, Object> response = RESTCaller.post("http://10.0.2.2:3000/addItem", body);
        boolean success = (boolean) response.get("success");
        //unsure how to pass in employee to get location data, as of now, var employee has no value
        if (success) {
            finish();
        } else {
            Toast toast = Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
