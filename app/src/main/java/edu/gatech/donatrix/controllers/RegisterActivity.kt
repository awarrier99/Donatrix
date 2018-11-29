package edu.gatech.donatrix.controllers

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

import java.util.HashMap

import edu.gatech.donatrix.R
import edu.gatech.donatrix.dao.LocationDao
import edu.gatech.donatrix.data.RESTCaller
import edu.gatech.donatrix.model.Location
import edu.gatech.donatrix.model.UserType

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var nameField: EditText? = null
    private var emailField: EditText? = null
    private var passwordField: EditText? = null
    private var confirmPasswordField: EditText? = null

    private var userType: UserType? = null
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        nameField = findViewById(R.id.registerNameInputText)
        emailField = findViewById(R.id.registerEmailInputText)
        passwordField = findViewById(R.id.registerPasswordInputText)
        confirmPasswordField = findViewById(R.id.registerConfirmPasswordInputText)
        val userTypeSpinner = findViewById<Spinner>(R.id.registerUserTypeSpinner)
        val locationSpinner = findViewById<Spinner>(R.id.registerLocationSpinner)
        userTypeSpinner.onItemSelectedListener = this
        locationSpinner.onItemSelectedListener = this

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, UserType.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userTypeSpinner.adapter = adapter

        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationDao.getLocations(this).toTypedArray())
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = locationAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val spinner = parent as Spinner
        if (spinner.id == R.id.registerUserTypeSpinner) {
            userType = spinner.getItemAtPosition(position) as UserType
        } else if (spinner.id == R.id.registerLocationSpinner) {
            location = parent.getItemAtPosition(position) as Location
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        userType = UserType.USER
        location = null
    }

    fun onRegisterPressed(view: View) {
        val password = "" + passwordField!!.text.toString()
        if (password == password && DataValidation.isPasswordStrong(password)) {
            val body = HashMap<String, Any>()
            body["email"] = "" + emailField!!.text

            var response = RESTCaller.post("https://donatrix-api.herokuapp.com/checkUser", body)
            var success = response["success"] as Boolean

            if (!success) {
                body["password"] = "" + passwordField!!.text
                body["name"] = "" + nameField!!.text
                body["locked"] = 0
                body["type"] = userType!!.type

                var clazz: Class<*> = LoginActivity::class.java

                if ("LOCATION_EMPLOYEE" == userType!!.type) {
                    body["loc_id"] = location!!.key

                } else if (userType!!.type == "MANAGER") {
                    clazz = ManagerHomeActivity::class.java
                }

                response = RESTCaller.post("https://donatrix-api.herokuapp.com/register", body)
                success = response["success"] as Boolean

                if (success) {
                    val intent = Intent(this@RegisterActivity, clazz)
                    startActivity(intent)
                } else {
                    val toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            } else {
                val toast = Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        } else {
            val toast = Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
