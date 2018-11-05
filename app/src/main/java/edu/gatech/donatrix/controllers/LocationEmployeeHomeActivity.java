package edu.gatech.donatrix.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import edu.gatech.donatrix.R;
import edu.gatech.donatrix.dao.UserDao;
import edu.gatech.donatrix.model.LocationEmployee;

public class LocationEmployeeHomeActivity extends AppCompatActivity {

    private LocationEmployee employee;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_location_employee);

        intent = getIntent();
        employee = (LocationEmployee) UserDao.getUser(intent.getStringExtra("employee_user"), this);
    }

    public void onLogoutPressed(View view) {
        finish();
    }

    public void onAddItemPressed(View view) {
        Intent intent = new Intent(LocationEmployeeHomeActivity.this, AddItemActivity.class);
        intent.putExtra("employee", employee);
        startActivity(intent);
    }

    public void onShowItemsButtonPressed(View view) {
        Log.d("Zeke", employee.getName());
        Intent intent = new Intent(LocationEmployeeHomeActivity.this, ItemListActivity.class);
        intent.putExtra("location_id", employee.getLocation().getKey());
        startActivity(intent);
    }
}
