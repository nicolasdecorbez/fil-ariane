package com.example.ardianethread.Activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.SetArdianeAdapter
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.NotificationsPushers.NotificationJourneyPusher
import com.example.ardianethread.NotificationsPushers.SmSPusher
import com.example.ardianethread.R
import com.example.ardianethread.Services.FriendServices
import com.example.ardianethread.Services.JourneyServices

class fragment_theseus_ardiane : Fragment() {
    val CHANNEL_ID: String = "mainChannel"

    private lateinit var friendView: RecyclerView
    private lateinit var friendList: ArrayList<Users>
    lateinit var friendName : Array<String>
    lateinit var friendNumber : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        createNotificationChannel()
        var v = inflater.inflate(R.layout.fragment_theseus_ardiane, container, false)
        var setJourney = v.findViewById<Button>(R.id.start_journey_button)

        friendView = v.findViewById<RecyclerView>(R.id.ardiane_view)
        friendView.layoutManager = LinearLayoutManager(this.context)
        friendView.setHasFixedSize(true)


        friendList = Global.TempFriendUser.UsersArray

        var adapter = SetArdianeAdapter(friendList)
        friendView.adapter = adapter



        setJourney.setOnClickListener {
            sendNotification()
            //CREATE THE NOTIFICATION WITH ALARM MANAGER FOR THE JOURNEY
            val intent = Intent(this.context, NotificationJourneyPusher::class.java)
            val alarm: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val pending = PendingIntent.getBroadcast(this.context,0,intent,0)
            alarm.set(AlarmManager.RTC_WAKEUP,Global.CurrentJourneys.currentJourneyTimer,pending)

            //CREATE EMERGENCY MESSAGE
            if (this.context?.let { it1 -> ContextCompat.checkSelfPermission(it1, android.Manifest.permission.SEND_SMS) }
                == PackageManager.PERMISSION_GRANTED){
                sendMessage()
            } else {
                var perm: Array<String> = arrayOf(
                    android.Manifest.permission.SEND_SMS
                )
                ActivityCompat.requestPermissions(requireActivity(),perm,100,)
            }
            //POST JOURNEY
//            this.context?.let { it1 ->
//                JourneyServices.Journey.PostJourney(2, 2, Global.CurrentJourneys.timestamp, 1.2F, 1.2F,
//                    it1)
//            }


            //REDIRECT TO MAIN ACTIVITY
            val activityIntent = Intent(this.context,TheseusHomeActivity::class.java)
            startActivity(activityIntent)
        }
        return v
    }


    private fun getFriendData() {
        for (i in friendName.indices) {
            val friend = Users(
                name = "unknown",
                firstname = friendName[i],
                phone = friendNumber[i],
                email = "unknown",
                blood_type = "unknown",
                hid = "unknown",
            )
            friendList.add(friend)
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "mainChannel"
            val descriptionText = "Channel for Event"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendMessage()
        } else {
            Toast.makeText(this.context,"Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    private fun sendNotification() {
        val builder = this.context?.let {
            NotificationCompat.Builder(it, "mainChannel")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                .setContentTitle("${Global.Current.User.firstname} ")
                .setContentText("requested you as Ardiane")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        val manager = this.context?.let { NotificationManagerCompat.from(it) }
        builder?.let { manager?.notify(200, it.build()) }
    }
    private fun sendMessage(){

        val intent = Intent(this.context, SmSPusher::class.java)
        val delayedMessage = 10 * 1000
        val alarm: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pending = PendingIntent.getBroadcast(this.context,0,intent,0)
        alarm.set(AlarmManager.RTC_WAKEUP,Global.CurrentJourneys.currentJourneyTimer + delayedMessage,pending)
    }
}