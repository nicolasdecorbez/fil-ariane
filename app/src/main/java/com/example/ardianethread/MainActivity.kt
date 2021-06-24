package com.example.ardianethread

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        var pager2 = findViewById<ViewPager2>(R.id.view_pager2)
        var adapter = FragmentAdapter(supportFragmentManager,lifecycle)

        pager2.adapter= adapter

        tabLayout.addTab(tabLayout.newTab().setText("Ariane"))
        tabLayout.addTab(tabLayout.newTab().setText("Theseus"))

        TabLayoutMediator(tabLayout,pager2){tab,position->
            when(position) {
                0 ->{
                    tab.text = "Ariane"
                }
                1->{
                    tab.text = "Theseus"
                }
            }
        }.attach()
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
            R.id.subitem_profile -> {
                val intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}