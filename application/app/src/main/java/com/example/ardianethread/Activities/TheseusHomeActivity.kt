package com.example.ardianethread.Activities


import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.fragment.app.*
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.NotificationsPushers.NotificationJourneyPusher
import com.example.ardianethread.NotificationsPushers.SmSPusher
import com.example.ardianethread.R
import com.example.ardianethread.Services.JourneyServices

class TheseusHomeActivity : AppCompatActivity() {
    val CHANNEL_ID: String = "mainChannel"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theseus__home)

        if (!Global.Current.isLoged){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            true
        }

        if (Global.CurrentJourneys.iterator < Global.CurrentJourneys.arrayId.size && Global.CurrentJourneys.isJourneyDefined)
        {
            Global.Current.User.id?.let {
                JourneyServices.Journey.PostJourney(Global.CurrentJourneys.arrayId[Global.CurrentJourneys.iterator],
                    it,
                    Global.CurrentJourneys.timestamp,
                    1.2F,
                    1.2F,
                    this)
            }
            Global.CurrentJourneys.iterator++
            val intent = Intent(this, TheseusHomeActivity::class.java)
            startActivity(intent)
        }
        if (Global.CurrentJourneys.iterator == Global.CurrentJourneys.arrayId.size && Global.CurrentJourneys.arrayId.size != 0 && !Global.CurrentJourneys.defined)
        {
            Global.CurrentJourneys.defined = true
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""
        //LOAD USER GLOBAL VARIABLE
        val username = findViewById<TextView>(R.id.theseus_user)
        username.text = Global.Current.User.firstname

        // LOAD JOURNEY FRAGMENT
        val start_button: Button = findViewById<Button>(R.id.start_button)
        val fragmentManager = supportFragmentManager
        start_button.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.theseus_container,fragment_theseus_timer())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        if (Global.CurrentJourneys.isJourneyDefined){
            val transaction = fragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.theseus_container,fragment_theseus_reset())
            transaction.addToBackStack(null)
            transaction.commit()
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