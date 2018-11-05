package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.ItemDao;
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
    private LocationEmployee employee;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

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
        //unsure how to pass in employee to get location data, as of now, var employee has no value
        ItemDao.addItem(employee, "" + sDescriptionField.getText(),  "" +
                fDescriptionField.getText(), Double.parseDouble(
                valueField.getText().toString()), itemCategory, "" +
                commentsField.getText(), this);
        Intent intent = new Intent(AddItemActivity.this, LocationEmployeeHomeActivity.class);
        startActivity(intent);
    }
}
