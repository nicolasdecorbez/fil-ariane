package com.example.ardianethread.Activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.ContactAdapter
import com.example.ardianethread.Adapters.MessageAdapter
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class fragment_conversation : Fragment() {

    private lateinit var messageView: RecyclerView
    private lateinit var messageList: ArrayList<Messages>
    lateinit var messageContent : Array<String>
    lateinit var messageContact : Array<String>

    var currentUser = Global.Current.User.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v =  inflater.inflate(R.layout.fragment_conversation, container, false)

        var title = v.findViewById<TextView>(R.id.textView2)
        title.text = Global.Corres.name



        messageView = v.findViewById<RecyclerView>(R.id.message_view)
        messageView.layoutManager = LinearLayoutManager(this.context)
        messageView.setHasFixedSize(true)

        messageContent = arrayOf(
            "I'm the sender message",
            "This is my message, a bit longer ",
            "I'm the sender answer, but this one is really and obviously longer, just to see a larger box",
            "I'm the last message of this conversation"
        )

        messageContact = arrayOf(
            Global.Corres.name,
            Global.Current.User.firstname,
            Global.Corres.name,
            Global.Current.User.firstname
        )

        messageList = arrayListOf<Messages>()
        getMessageData()

        var adapter = MessageAdapter(messageList)
        messageView.adapter = adapter
        return v
    }

    private fun getMessageData() {
        for (i in messageContent.indices) {
            val friend = Messages(
                content = messageContent[i],
                contact = messageContact[i]
            )
           messageList.add(friend)
        }
    }
    }