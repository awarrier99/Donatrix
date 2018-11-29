package edu.gatech.donatrix.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

import java.io.Serializable
import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedHashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.dao.LocationDao
import edu.gatech.donatrix.data.RESTCaller
import edu.gatech.donatrix.model.ItemCategory
import edu.gatech.donatrix.model.Location

class ItemSearchActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var nameText: EditText? = null
    private var resultSpinner: Spinner? = null

    private var location: Location? = null
    private var category: String? = null

    private var locations: List<Location>? = null

    private var item: String? = null
    private var items: List<Map<String, Any>>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)

        val locationSpinner = findViewById<Spinner>(R.id.itemSearchLocationSpinner)
        val categorySpinner = findViewById<Spinner>(R.id.itemSearchCategorySpinner)
        locationSpinner.onItemSelectedListener = this
        categorySpinner.onItemSelectedListener = this
        nameText = findViewById(R.id.editText)

        Log.d("Donatrix", "Hello")
        resultSpinner = findViewById(R.id.itemSearchResultSpinner)
        resultSpinner!!.onItemSelectedListener = this

        val locList = LocationDao.getLocations(this)
        locations = locList
        val locs = ArrayList<String?>()
        for (loc in locList) {
            locs.add(loc.name)
        }
        locs.add(0, "")
        val locationArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locs.toTypedArray())
        locationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = locationArrayAdapter

        Log.d("Donatrix", "Hello2")
        val itemCategoryArray = ItemCategory.values()
        val categories = ArrayList<String>()
        for (i in itemCategoryArray) {
            categories.add(i.category)
        }
        categories.add(0, "")
        val itemCategoryArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories.toTypedArray())
        Log.d("Donatrix", "Hello3")
        itemCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Log.d("Donatrix", "Hello5")
        categorySpinner.adapter = itemCategoryArrayAdapter
        Log.d("Donatrix", "Hello4")

        val result = arrayOfNulls<String>(1)
        result[0] = ""
        val stringArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, result)
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        resultSpinner!!.adapter = stringArrayAdapter
        Log.d("Donatrix", "Hello6")
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        try {
            val spinner = parent as Spinner
            if (spinner.id == R.id.itemSearchLocationSpinner) {
                var changed = false
                for (loc in locations!!) {
                    if (loc.name == spinner.getItemAtPosition(position)) {
                        location = loc
                        changed = true
                    }
                }
                if (!changed) {
                    location = null
                }
            } else if (spinner.id == R.id.itemSearchCategorySpinner) {
                category = spinner.getItemAtPosition(position) as String
            } else if (spinner.id == R.id.itemSearchResultSpinner) {
                item = spinner.getItemAtPosition(position) as String
            }
            Log.d("Donatrix", "Please Print")
        } catch (e: Exception) {
            Log.d("Donatrix", e.message)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        category = ""
        location = null
    }

    fun onCancelButtonPressed(view: View) {
        finish()
    }

    fun onSearchButtonPressed(view: View) {
        val body = HashMap<String, Any>()
        if (location != null) {
            body["loc_id"] = location!!.key
        }
        if (!category!!.isEmpty()) {
            body["cat"] = category!!
        }
        if (!("" + nameText!!.text).isEmpty()) {
            body["sDesc"] = "" + nameText!!.text
        }
        Log.d("Donatrix", body.toString())
        val response = RESTCaller.post("https://donatrix-api.herokuapp.com/filtered/getItems", body)
        val success = response["success"] as Boolean
        if (success) {
            val res = response["items"] as List<Map<String, Any>>?
            items = res
            val result = ArrayList<String>()
            for (m in res!!) {
                Log.d("Donatrix", m.toString())
                result.add(m["s_description"] as String)
            }
            val stringArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, result.toTypedArray())
            stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            resultSpinner!!.adapter = stringArrayAdapter
        }
    }

    fun onDetailsButtonPressed(view: View) {
        val itemName = resultSpinner!!.getItemAtPosition(0) as String
        var item: Map<String, Any> = LinkedHashMap()
        for (m in items!!) {
            if (m["s_description"] == this.item) {
                item = m
            }
        }
        val intent = Intent(this@ItemSearchActivity, ItemDetailActivity::class.java)
        intent.putExtra("item", item as Serializable)
        startActivity(intent)
    }
}
