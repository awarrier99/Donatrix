package edu.gatech.donatrix.controllers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import edu.gatech.donatrix.R

class LocationEmployeeHomeActivity : AppCompatActivity() {

    private var locationId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_location_employee)

        val intent = intent
        locationId = intent.getIntExtra("location_id", 0)
    }

    fun onLogoutPressed(view: View) {
        finish()
    }

    fun onAddItemPressed(view: View) {
        val intent = Intent(this@LocationEmployeeHomeActivity, AddItemActivity::class.java)
        intent.putExtra("location_id", locationId)
        startActivity(intent)
    }

    fun onShowItemsButtonPressed(view: View) {
        val intent = Intent(this@LocationEmployeeHomeActivity, ItemListActivity::class.java)
        intent.putExtra("location_id", locationId)
        intent.putExtra("all_items", false)
        startActivity(intent)
    }

    fun onShowAllItemsPressed(view: View) {
        val intent = Intent(this@LocationEmployeeHomeActivity, ItemListActivity::class.java)
        intent.putExtra("location_id", locationId)
        intent.putExtra("all_items", true)
        startActivity(intent)
    }

    fun onSearchItemsPressed(view: View) {
        try {
            val intent = Intent(this, ItemSearchActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.d("Donatrix", e.message)
        }

    }

    fun onLocationListPressed(view: View) {
        val intent = Intent(this, LocationListActivity::class.java)
        startActivity(intent)
    }
}
