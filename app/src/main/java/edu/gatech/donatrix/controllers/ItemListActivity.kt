package edu.gatech.donatrix.controllers


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

import java.io.Serializable
import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedHashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.data.RESTCaller

class ItemListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var item: String? = null
    private var items: List<Map<String, Any>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val intent = intent
        val locationId = intent.getIntExtra("location_id", 0)
        val allItems = intent.getBooleanExtra("all_items", false)

        if (locationId == 0) {
            val toast = Toast.makeText(this, "Location not passed in", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            finish()
        }

        val itemSpinner = findViewById<Spinner>(R.id.itemListItemSpinner)
        itemSpinner.onItemSelectedListener = this

        try {
            val body = HashMap<String, Any>()
            body["loc_id"] = locationId

            val response: Map<String, Any>
            if (!allItems) {
                response = RESTCaller.post("https://donatrix-api.herokuapp.com/location/getItems", body)
            } else {
                response = RESTCaller.get("https://donatrix-api.herokuapp.com/allItems")
            }

            val success = response["success"] as Boolean

            if (success) {
                val fullItems = response["items"] as ArrayList<Map<String, Any>>?
                this.items = fullItems
                val items = ArrayList<String>()
                for (m in fullItems!!) {
                    items.add(m["s_description"] as String)
                }
                val itemArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
                itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                itemSpinner.adapter = itemArrayAdapter
            } else {
                val toast = Toast.makeText(this, "There are no items at this Location", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                val empty = arrayOfNulls<String>(0)
                val itemArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, empty)
                itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                itemSpinner.adapter = itemArrayAdapter
            }
        } catch (e: Exception) {
            Log.d("Zeke2", e.message)
            val toast = Toast.makeText(this, "There are no items at this Location", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            val empty = arrayOfNulls<String>(0)
            val itemArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, empty)
            itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            itemSpinner.adapter = itemArrayAdapter
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        item = parent.getItemAtPosition(position) as String
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        item = null
    }

    fun onCancelButtonPressed(view: View) {
        finish()
    }

    fun onDetailsButtonPressed(view: View) {
        var item: Map<String, Any> = LinkedHashMap()
        for (m in items!!) {
            if (m["s_description"] == this.item) {
                item = m
            }
        }
        val intent = Intent(this@ItemListActivity, ItemDetailActivity::class.java)
        intent.putExtra("item", item as Serializable)
        startActivity(intent)
    }

}
