package edu.gatech.donatrix.controllers

import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import edu.gatech.donatrix.R
import edu.gatech.donatrix.data.RESTCaller
import edu.gatech.donatrix.model.ItemCategory

class AddItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var sDescriptionField: EditText? = null
    private var fDescriptionField: EditText? = null
    private var valueField: EditText? = null
    private var commentsField: EditText? = null

    private var itemCategory: ItemCategory? = null
    private var locationId: Int = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val intent = intent
        locationId = intent.getIntExtra("location_id", 0)

        sDescriptionField = findViewById(R.id.addItemSDescription)
        fDescriptionField = findViewById(R.id.addItemFDescription)
        valueField = findViewById(R.id.addItemValue)
        commentsField = findViewById(R.id.addItemComments)
        val itemCategorySpinner = findViewById<Spinner>(R.id.addItemCategory)
        itemCategorySpinner.onItemSelectedListener = this

        val adapter = object : ArrayAdapter<ItemCategory>(this, android.R.layout.simple_spinner_item,
                ItemCategory.values()) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        itemCategorySpinner.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        itemCategory = parent.getItemAtPosition(position) as ItemCategory
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        //do nothing
    }

    fun onAddItemPressed(view: View) {
        Log.d("Donatrix", Integer.valueOf(locationId).toString())
        val body:MutableMap<String, Any> = mutableMapOf()
        body["sDesc"] = "" + sDescriptionField!!.text
        body["fDesc"] = "" + fDescriptionField!!.text
        body["value"] = java.lang.Double.parseDouble(valueField!!.text.toString())
        body["cat"] = itemCategory!!.category
        body["comments"] = "" + commentsField!!.text
        body["loc_id"] = locationId

        val response = RESTCaller.post("https://donatrix-api.herokuapp.com/addItem", body)
        val success = response["success"] as Boolean
        //unsure how to pass in employee to get location data, as of now, var employee has no value
        if (success) {
            finish()
        } else {
            val toast = Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
