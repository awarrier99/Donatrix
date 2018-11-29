package edu.gatech.donatrix.controllers

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import edu.gatech.donatrix.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun onLoginPressed(view: View) {
        val loginActivityIntent = Intent(this@WelcomeActivity, LoginActivity::class.java)
        startActivity(loginActivityIntent)
    }

    fun onRegisterPressed(view: View) {
        val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun onGuestLoginPressed(view: View) {
        val intent = Intent(this@WelcomeActivity, GuestHomeActivity::class.java)
        startActivity(intent)
    }
}
