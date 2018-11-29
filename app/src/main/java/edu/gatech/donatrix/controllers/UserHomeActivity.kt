package edu.gatech.donatrix.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import edu.gatech.donatrix.R

class UserHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_user)
    }

    fun onLocationListPressed(view: View) {
        val intent = Intent(this, LocationListActivity::class.java)
        startActivity(intent)
    }
}
