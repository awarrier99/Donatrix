package edu.gatech.donatrix.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.ItemDao;
import edu.gatech.donatrix.model.Item;

public class ItemListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner itemSpinner;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemSpinner = (Spinner) findViewById(R.id.itemListItemSpinner);
        itemSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<Item> itemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ItemDao.getAllItems(this).toArray());
        itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(itemArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = (Item) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { item = null; }
}
