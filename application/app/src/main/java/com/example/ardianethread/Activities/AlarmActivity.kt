package com.example.ardianethread.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Tones
import com.example.ardianethread.R
import com.example.ardianethread.Adapters.ToneAdapter
import com.example.ardianethread.Globals.Global

class AlarmActivity : AppCompatActivity() {

    private lateinit var toneView: RecyclerView
    private lateinit var toneList: ArrayList<Tones>
    lateinit var toneTitle : Array<String>


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        if (!Global.Current.isLoged){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            true
        }
        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""


        // EXPAND TONES BUTTON//

        val toogle_button_tone = findViewById<Button>(R.id.tone_button)

        toneView= findViewById<RecyclerView>(R.id.message_view)
        toneView.layoutManager = LinearLayoutManager(this)
        toneView.setHasFixedSize(true)

        var is_expanded = false

        toogle_button_tone.setOnClickListener() {
            if(!is_expanded) {
                is_expanded = true
                toneView.visibility = VISIBLE
            } else {
                is_expanded = false
                toneView.visibility = GONE
            }
        }
        //HANDLE SWITCH RING MODE BUTTON

        var switch_button = findViewById<Switch>(R.id.switch_ring_mode)

        switch_button.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switch_button.text = "Ring Mode"
            } else {
                switch_button.text = "Vibrate Mode"
            }
        }

        //POPULATE TONE VIEW//

        toneTitle = arrayOf(
            "Metallica -- Through The Never",
            "Steevie Wonder -- Superstition",
            "Doc Watson -- Black Mountain Rag"
        )

        toneList = arrayListOf<Tones>()
        getTonesData()

        toneView.adapter = ToneAdapter(toneList)

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

    private fun getTonesData(){
        for(i in toneTitle.indices){
            val tone = Tones(toneTitle[i])
            toneList.add(tone)
        }
    }
    }