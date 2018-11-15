package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.data.RESTCaller;

/**
 * An Activity for Item Detail
 */
public class ItemDetailActivity extends AppCompatActivity {

    /**
     * Activity initializer
     *
     * @param savedInstanceState the activity state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();
        Map<String, Object> item = (Map<String, Object>) intent.getSerializableExtra("item");

        TextView sDescText = findViewById(R.id.textView3);
        TextView fDescText = findViewById(R.id.textView5);
        TextView valueText = findViewById(R.id.textView7);
        TextView commentsText = findViewById(R.id.textView9);
        TextView locationText = findViewById(R.id.textView11);

        sDescText.setText((String) item.get("s_description"));
        fDescText.setText((String) item.get("l_description"));
        valueText.setText(Objects.requireNonNull(item.get("Value")).toString());
        commentsText.setText((String) item.get("Comments"));

        Map<String, Object> body = new HashMap<>();
        body.put("loc_id", Objects.requireNonNull(item.get("location")));

        Map<String, Object> response = RESTCaller.post(
                "https://donatrix-api.herokuapp.com/location", body);
        boolean success = (boolean) response.get("success");

        if (success) {
            locationText.setText((String) ((Map<String, Object>) Objects.requireNonNull(
                    response.get("location"))).get("Name"));
        }
    }

    /**
     * What happens when Cancel?
     * @param view Something to make this work.
     */
    public void onCancelButtonPressed(View view) {
        finish();
    }
}
