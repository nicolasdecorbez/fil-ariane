package com.example.ardianethread.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R
import com.example.ardianethread.Services.FriendServices

class fragment_friend_add : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_friend_add, container, false)
        var friend_request_button: Button = v.findViewById(R.id.friend_request_button)
        var friend_phone: EditText = v.findViewById(R.id.editTextPhone)

        friend_request_button.setOnClickListener {
            Toast.makeText(context, "Request Sent", Toast.LENGTH_SHORT).show()

            this.context?.let { it1 ->
                FriendServices.Friend.addFriend(Global.Current.User.id!!,
                    friend_phone.text.toString(),
                    it1)
            }
            ResetTempUsersGlobals()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return v
    }

    fun ResetTempUsersGlobals() {
        Global.TempFriendUser.arrayDefined = false
        Global.TempFriendUser.idDefined = false
        Global.TempFriendUser.UsersArray = arrayListOf()
        Global.TempFriendUser.Ids = arrayListOf()
        Global.TempFriendUser.iterator=0
    }
}