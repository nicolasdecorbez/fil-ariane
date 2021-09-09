package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        if (!Global.Current.isLoged){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }

        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""

        var switch_button__message = findViewById<Switch>(R.id.switch_notif_message)

        switch_button__message.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switch_button__message.text = resources.getString(R.string.notif_message_on)
            } else {
                switch_button__message.text = resources.getString(R.string.notif_message_off)
            }
        }

        var switch_button_update = findViewById<Switch>(R.id.switch_notif_update)

        switch_button_update.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switch_button_update.text = resources.getString(R.string.notif_update_on)
            } else {
                switch_button_update.text = resources.getString(R.string.notif_update_off)
            }
        }

        var switch_button_friend = findViewById<Switch>(R.id.switch_notif_friend)

        switch_button_friend.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switch_button_friend.text = resources.getString(R.string.notif_friend_on)
            } else {
                switch_button_friend.text = resources.getString(R.string.notif_friend_on)
            }
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
}