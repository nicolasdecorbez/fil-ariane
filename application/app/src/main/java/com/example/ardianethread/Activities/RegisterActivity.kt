package com.example.ardianethread.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R
import com.example.ardianethread.Services.MessageServices
import com.example.ardianethread.Services.UserServices
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var name_field : EditText = findViewById(R.id.editTextPersonName)
        var firstname_field : EditText = findViewById(R.id.editTextPersonName2)
        var phone_field : EditText = findViewById(R.id.editTextPhone)
        var email_field : EditText = findViewById(R.id.editTextEmailAddress)
        var submit_button : Button = findViewById<Button>(R.id.register_button)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        var isLoaded = false

        submit_button.setOnClickListener {
           //REGISTER
            UserServices.User.Register(
                firstname_field.text.toString(),
                name_field.text.toString(),
                email_field.text.toString(),
                phone_field.text.toString(),
                this )

            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.register_container, fragment_more_register())
            transaction.commitNow()
            isLoaded = true

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.home_button -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_alarm -> {
                val intent = Intent(this, AlarmActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.contact_button ->{
                val intent = Intent(this, FriendActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.message_button ->{
                val intent = Intent(this, MessageActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_Login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.subitem_notif -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}