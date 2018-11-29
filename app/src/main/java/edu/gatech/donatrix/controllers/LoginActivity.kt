package edu.gatech.donatrix.controllers

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast

import java.util.HashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.data.RESTCaller
import edu.gatech.donatrix.model.User

class LoginActivity : AppCompatActivity() {

    private var emailField: EditText? = null
    private var passwordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        emailField = findViewById(R.id.loginEmailTextField)
        passwordField = findViewById(R.id.loginPasswordTextField)
    }

    fun onLoginPressed(view: View) {
        val body = HashMap<String, Any>()
        body["email"] = "" + emailField!!.text
        body["password"] = "" + passwordField!!.text

        val response = RESTCaller.post("https://donatrix-api.herokuapp.com/login", body)
        val success = User.testLoggedIn(body)
        val user = response["user"] as Map<String, Any>?

        if (success) {
            val type = user!!["type"] as String?
            if ("USER" == type) {
                val intent = Intent(this@LoginActivity, UserHomeActivity::class.java)
                startActivity(intent)
            } else if (type == "ADMIN") {
                val intent = Intent(this@LoginActivity, AdminHomeActivity::class.java)
                startActivity(intent)
            } else if (type == "LOCATION_EMPLOYEE") {
                val intent = Intent(this@LoginActivity, LocationEmployeeHomeActivity::class.java)
                intent.putExtra("location_id", user["loc_id"] as Int)
                startActivity(intent)
            } else {
                val intent = Intent(this@LoginActivity, ManagerHomeActivity::class.java)
                startActivity(intent)
            }

        } else {
            val toast = Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
