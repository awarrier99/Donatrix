package edu.gatech.donatrix.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import java.util.HashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.data.RESTCaller

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val intent = intent
        val item = intent.getSerializableExtra("item") as Map<String, Any>

        val sDescText = findViewById<TextView>(R.id.textView3)
        val fDescText = findViewById<TextView>(R.id.textView5)
        val valueText = findViewById<TextView>(R.id.textView7)
        val commentsText = findViewById<TextView>(R.id.textView9)
        val locationText = findViewById<TextView>(R.id.textView11)

        sDescText.text = item["s_description"] as String?
        fDescText.text = item["l_description"] as String?
        valueText.text = item["Value"]!!.toString()
        commentsText.text = item["Comments"] as String?

        val body = HashMap<String, Any>()
        body["loc_id"] = item["location"]!!

        val response = RESTCaller.post("https://donatrix-api.herokuapp.com/location", body)
        val success = response["success"] as Boolean

        if (success) {
            locationText.text = (response["location"] as Map<String, Any>)["Name"] as String?
        }
    }

    fun onCancelButtonPressed(view: View) {
        finish()
    }
}
