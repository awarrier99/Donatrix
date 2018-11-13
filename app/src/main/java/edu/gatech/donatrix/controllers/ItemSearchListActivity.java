package edu.gatech.donatrix.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.LocationDao;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.ItemCategory;
import edu.gatech.donatrix.model.Location;

public class ItemSearchListActivity extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_list);

        listView = (ListView) findViewById(R.id.list_view);
        list.add("Orange");
        list.add("Orange");
        list.add("Orange");
        list.add("Orange");
        list.add("Orange");
        list.add("Orange");

        adapter = new ArrayAdapter(ItemSearchListActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}