package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
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
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.ItemCategory;

/**
 * An activity to add an item
 */
public class AddItemActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private EditText sDescriptionField;
    private EditText fDescriptionField;
    private EditText valueField;
    private EditText commentsField;

    private ItemCategory itemCategory;
    private int locationId;

    /**
     * Activity inializer
     *
     * @param savedInstanceState the saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intent = getIntent();
        locationId = intent.getIntExtra("location_id", 0);

        sDescriptionField = findViewById(R.id.addItemSDescription);
        fDescriptionField = findViewById(R.id.addItemFDescription);
        valueField = findViewById(R.id.addItemValue);
        commentsField = findViewById(R.id.addItemComments);
        Spinner itemCategorySpinner = findViewById(R.id.addItemCategory);
        itemCategorySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<ItemCategory> adapter = new ArrayAdapter<ItemCategory>(
                this, android.R.layout.simple_spinner_item,
                ItemCategory.values()){
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
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

    /**
     * Callback when an item is selected
     *
     * @param parent parent of item selection
     * @param view current view
     * @param position position of item
     * @param id id of item selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemCategory = (ItemCategory) parent.getItemAtPosition(position);
    }

    /**
     * Does nothing. Satisfies interface
     *
     * @param parent pointless
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }

    /**
     * A method to handle a press
     * @param view Something here
     */
    public void onAddItemPressed(View view) {
        Log.d("Donatrix", Integer.valueOf(locationId).toString());
        Map<String, Object> body = new HashMap<>();
        body.put("sDesc","" + sDescriptionField.getText());
        body.put("fDesc", "" + fDescriptionField.getText());
        body.put("value", Double.parseDouble(valueField.getText().toString()));
        body.put("cat", itemCategory.getCategory());
        body.put("comments", "" + commentsField.getText());
        body.put("loc_id", locationId);

        Map<String, Object> response = RESTCaller.post(
                "https://donatrix-api.herokuapp.com/addItem", body);
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
