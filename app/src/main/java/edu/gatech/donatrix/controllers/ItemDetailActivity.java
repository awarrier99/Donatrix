package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.model.Item;

public class ItemDetailActivity extends AppCompatActivity {

    private Item item;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");

    }
}
