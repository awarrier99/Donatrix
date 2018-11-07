package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.data.RESTCaller;
import edu.gatech.donatrix.model.Item;

public class ItemDetailActivity extends AppCompatActivity {

    private Map<String, Object> item;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        intent = getIntent();
        item = (Map<String, Object>) intent.getSerializableExtra("item");

        TextView sDescText = (TextView) findViewById(R.id.textView3);
        TextView fDescText = (TextView) findViewById(R.id.textView5);
        TextView valueText = (TextView) findViewById(R.id.textView7);
        TextView commentsText = (TextView) findViewById(R.id.textView9);
        TextView locationText = (TextView) findViewById(R.id.textView11);

        sDescText.setText((String) item.get("s_description"));
        fDescText.setText((String) item.get("l_description"));
        valueText.setText(((Integer) item.get("Value")).toString());
        commentsText.setText((String) item.get("Comments"));

        Map<String, Object> body = new HashMap<>();
        body.put("loc_id", (int) item.get("location"));

        Map<String, Object> response = RESTCaller.post("https://donatrix-api.herokuapp.com/location", body);
        boolean success = (boolean) response.get("success");

        if (success) {
            locationText.setText((String) ((Map<String, Object>) response.get("location")).get("Name"));
        }
    }

    public void onCancelButtonPressed(View view) {
        finish();
    }
}
