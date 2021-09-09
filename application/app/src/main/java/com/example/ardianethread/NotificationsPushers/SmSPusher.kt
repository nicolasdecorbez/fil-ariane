package com.example.ardianethread.NotificationsPushers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ardianethread.R

class SmSPusher : BroadcastReceiver() {
    val CHANNEL_ID :String = "mainChannel"

    override fun onReceive(context: Context, intent: Intent?) {

        var phoneNumber = "+1 555-521-5554"
        var text = "I'm the Test Message"

        var smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber,null,text,null,null)
    }
}