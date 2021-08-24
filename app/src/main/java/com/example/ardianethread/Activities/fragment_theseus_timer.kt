package com.example.ardianethread.Activities

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R
import java.text.SimpleDateFormat
import java.util.*


class fragment_theseus_timer : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_theseus_timer, container, false)

        var isJourney = false
        var timer_button = v.findViewById<Button>(R.id.set_timer_button)
        var timer_display = v.findViewById<TextView>(R.id.timer_display)
        var set_ardiane_button : Button = v.findViewById(R.id.set_ardiane_button)
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var calendar =  Calendar.getInstance()

        timer_button.setOnClickListener{
            var timePicker = TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
                calendar.set(Calendar.MINUTE,minute)
                timer_display.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(this.context,timePicker,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show()
            isJourney = true
        }
        set_ardiane_button.setOnClickListener{
            transaction.setReorderingAllowed(true)
            transaction.replace(R.id.theseus_container, fragment_theseus_ardiane())
            transaction.commit()
            if(isJourney){
                Global.CurrentJourneys.inflateJourneyInfo(Global.Current.User.firstname,SimpleDateFormat("HH:mm").format(calendar.time))
            }
        }
        return v
    }
}