package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ardianethread.R

class ArdianeHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ardiane_home)
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
            R.id.subitem_notif -> {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}