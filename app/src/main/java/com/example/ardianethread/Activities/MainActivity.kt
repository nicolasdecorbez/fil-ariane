package com.example.ardianethread.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.ardianethread.Adapters.FragmentAdapter
import com.example.ardianethread.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SETUP FOR TABS
        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        var pager2 = findViewById<ViewPager2>(R.id.view_pager2)
        var adapter = FragmentAdapter(supportFragmentManager,lifecycle)

        pager2.adapter= adapter
        tabLayout.addTab(tabLayout.newTab().setText("Ariane"))
        tabLayout.addTab(tabLayout.newTab().setText("Theseus"))

        TabLayoutMediator(tabLayout,pager2){tab,position->
            when(position) {
                0 -> {
                    tab.text = "Ariane"

                }
                1->{
                    tab.text = "Theseus"
                }
            }
        }.attach()
        //SET TOOLBAR
        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""
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