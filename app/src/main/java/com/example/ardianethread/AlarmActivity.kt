package com.example.ardianethread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.ui.main.ToneAdapter

class AlarmActivity : AppCompatActivity() {

    private lateinit var toneView: RecyclerView
    private lateinit var toneList: ArrayList<Tones>
    lateinit var toneTitle : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        setSupportActionBar(findViewById(R.id.aToolbar))
        title = ""


        // EXPAND TONES BUTTON//

        val toogle_button_tone = findViewById<Button>(R.id.tone_button)

        toneView= findViewById<RecyclerView>(R.id.tone_view)
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
        switch_button.textOff = "Vibrate Mode"
        switch_button.textOn =" Ring Mode"

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

    private fun getTonesData(){
        for(i in toneTitle.indices){
            val tone = Tones(toneTitle[i])
            toneList.add(tone)
        }
    }
    }