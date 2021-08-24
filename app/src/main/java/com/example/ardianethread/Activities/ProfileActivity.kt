package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var name_field : EditText = findViewById(R.id.editTextPersonName)
        var firstname_field : EditText = findViewById(R.id.editTextPersonName2)
        var phone_field : EditText = findViewById(R.id.editTextPhone)
        var email_field : EditText = findViewById(R.id.editTextEmailAddress)
        var blood_field : EditText = findViewById(R.id.editTextBloodType)
        var hid_field : EditText = findViewById(R.id.editTextHid)
        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""

        if (Global.Current.isDefined) {
            setUser()
        }

        var submit_button : Button = findViewById<Button>(R.id.outlinedButton)

        submit_button.setOnClickListener {
           val user = Users(
               name = name_field.text.toString(),
               firstname = firstname_field.text.toString(),
               phone = phone_field.text.toString(),
               email = email_field.text.toString(),
               blood_type = blood_field.text.toString(),
               hid = hid_field.text.toString()
               )


            Global.Current.User = user
            Global.Current.isDefined = true
            //TO DO: CALL POST API ROUTE FOR USERS


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

    public fun setUser(){

        var name_field : EditText = findViewById(R.id.editTextPersonName)
        var firstname_field : EditText = findViewById(R.id.editTextPersonName2)
        var phone_field : EditText = findViewById(R.id.editTextPhone)
        var email_field : EditText = findViewById(R.id.editTextEmailAddress)
        var blood_field : EditText = findViewById(R.id.editTextBloodType)
        var hid_field : EditText = findViewById(R.id.editTextHid)

        name_field.setText( Global.Current.User.name)
        firstname_field.setText( Global.Current.User.firstname)
        phone_field.setText( Global.Current.User.phone)
        email_field.setText( Global.Current.User.email)
        blood_field.setText( Global.Current.User.blood_type)
        hid_field.setText( Global.Current.User.hid)
    }
}