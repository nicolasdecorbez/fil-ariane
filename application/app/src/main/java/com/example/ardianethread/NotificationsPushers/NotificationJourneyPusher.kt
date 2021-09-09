package com.example.ardianethread.NotificationsPushers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ardianethread.R

class NotificationJourneyPusher: BroadcastReceiver() {
    val CHANNEL_ID :String = "mainChannel"

    override fun onReceive(context: Context, intent: Intent?) {

        val builder = NotificationCompat.Builder(context, "mainChannel")
            .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
            .setContentTitle("Ariadne's Thread")
            .setContentText("Let us know if you're back")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = NotificationManagerCompat.from(context)

        manager.notify(200, builder.build())
    }
}