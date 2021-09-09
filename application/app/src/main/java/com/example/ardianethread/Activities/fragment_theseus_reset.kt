package com.example.ardianethread.Activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.NotificationsPushers.NotificationJourneyPusher
import com.example.ardianethread.NotificationsPushers.SmSPusher
import com.example.ardianethread.R
import java.text.SimpleDateFormat
import java.util.*

class fragment_theseus_reset : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var v = inflater.inflate(R.layout.fragment_theseus_reset, container, false)
        var reset_timer = v.findViewById<Button>(R.id.reset_timer_button)
        var end_journey = v.findViewById<Button>(R.id.end_journey)
        var calendar =  Calendar.getInstance()

        reset_timer.setOnClickListener {
            var timePicker =
                TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    //CREATE TIMERPICKER
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    var selectedTime = SimpleDateFormat("HH:mm").format(calendar.time)

                    //SAVING NEW JOURNEY'S GLOBALS
                    Global.CurrentJourneys.theseusJourney.timer = selectedTime
                    Global.CurrentJourneys.theseusJourney.user = Global.Current.User.firstname
                    Global.CurrentJourneys.updateJourneyEntry(Global.Current.User.firstname,selectedTime)
                    Global.CurrentJourneys.timestamp  = SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(calendar.time)
                    Global.CurrentJourneys.currentJourneyTimer = calendar.timeInMillis

                    //SETTING UP NOTIFICATION WITH ALARM MANAGER
                    val alarmintent = Intent(this.context, NotificationJourneyPusher::class.java)
                    val alarm: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val pending = PendingIntent.getBroadcast(this.context,0,alarmintent,0)
                    alarm.set(AlarmManager.RTC_WAKEUP,Global.CurrentJourneys.currentJourneyTimer,pending)

                    // SETTING UP EMERGENCY SMS
                    if (this.context?.let { it1 -> ContextCompat.checkSelfPermission(it1, android.Manifest.permission.SEND_SMS) }
                        == PackageManager.PERMISSION_GRANTED){
                        sendMessage()
                    } else {
                        var perm: Array<String> = arrayOf(
                            android.Manifest.permission.SEND_SMS
                        )
                        ActivityCompat.requestPermissions(requireActivity(),perm,100,)
                    }
                    //REDIRECT TO MAIN ACTIVITY
                    val intent = Intent(this.context, MainActivity::class.java)
                    startActivity(intent)
                }

            TimePickerDialog(this.context,timePicker,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show()

        }

        end_journey.setOnClickListener {
            Global.CurrentJourneys.isJourneyDefined =false
            Global.CurrentJourneys.arrayId = arrayListOf()
            Global.CurrentJourneys.defined = false
            Global.CurrentJourneys.iterator = 0
            var position = Global.CurrentJourneys.findJourneyinArray(Global.Current.User.firstname)
            Global.CurrentJourneys.deleteJourneyEntry(Global.Current.User.firstname)
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return v
    }
    private fun sendMessage(){

        val intent = Intent(this.context, SmSPusher::class.java)
        val delayedMessage = 10 * 1000
        val alarm: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pending = PendingIntent.getBroadcast(this.context,0,intent,0)
        alarm.set(AlarmManager.RTC_WAKEUP,Global.CurrentJourneys.currentJourneyTimer + delayedMessage,pending)
    }
}