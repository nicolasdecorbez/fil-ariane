package com.example.ardianethread.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

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
            val user = Users(
                name = name_field.text.toString(),
                firstname = firstname_field.text.toString(),
                phone = phone_field.text.toString(),
                email = email_field.text.toString(),
                blood_type = R.string.user_bt.toString(),
                hid = R.string.user_hid.toString(),
            )
            Global.Current.User = user
            Global.Current.isDefined = true


            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.register_container, fragment_more_register())
            transaction.commitNow()
            isLoaded = true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu,menu)
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