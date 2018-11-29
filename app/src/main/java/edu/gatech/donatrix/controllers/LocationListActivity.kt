package edu.gatech.donatrix.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import edu.gatech.donatrix.R
import edu.gatech.donatrix.dao.LocationDao
import edu.gatech.donatrix.model.Location

class LocationListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_list)

        val locationSpinner = findViewById<Spinner>(R.id.locationListLocationSpinner)
        locationSpinner.onItemSelectedListener = this

        val locationArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationDao.getLocations(this).toTypedArray())
        locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = locationArrayAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        location = parent.getItemAtPosition(position) as Location
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        location = null
    }


    fun onBackButtonPressed(view: View) {
        finish()
    }

    fun onChooseButtonPressed(view: View) {
        Log.d("Zeke", location!!.name)
        val intent = Intent(this@LocationListActivity, ItemListActivity::class.java)
        intent.putExtra("location_id", location!!.key)
        startActivity(intent)
    }
}
