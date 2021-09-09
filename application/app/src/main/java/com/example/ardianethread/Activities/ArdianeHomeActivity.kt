package com.example.ardianethread.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.JourneyAdapter
import com.example.ardianethread.Data.Journey
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class ArdianeHomeActivity : AppCompatActivity() {

    private lateinit var friendView: RecyclerView
    lateinit var friendName: Array<String>
    lateinit var friendNumber: Array<String>
    var journeyList: ArrayList<Journey> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ardiane_home)

        if (!Global.Current.isLoged){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            true
        }

        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""

        //LOAD USER GLOBAL VARIABLE
        val username = findViewById<TextView>(R.id.ardiane_user)
        username.text = Global.Current.User.firstname

        friendView = findViewById<RecyclerView>(R.id.friend_view)
        friendView.layoutManager = LinearLayoutManager(this)
        friendView.setHasFixedSize(true)

        if (Global.CurrentJourneys.isJourneyDefined) {
            journeyList = Global.CurrentJourneys.GlobalJourneyList
        } else {
            var mockJourney = Journey(
                user = "No Journey Yet",
                timer = "00:00"
            )
            journeyList.add(mockJourney)
        }
        var adapter = JourneyAdapter(journeyList)
        friendView.adapter = adapter
        val intent = Intent(this, MapsActivity::class.java)
        adapter.setOnItemClickListener(object : JourneyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                super.onItemClick(position)
                startActivity(intent)
            }
        })
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
            R.id.contact_button -> {
                val intent = Intent(this, FriendActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.message_button -> {
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

    private fun getFriendData() {
        for (i in friendName.indices) {
            val mock = Journey(
               user = friendName[i],
                timer = friendNumber[i]
            )
            journeyList.add(mock)
        }
    }
}