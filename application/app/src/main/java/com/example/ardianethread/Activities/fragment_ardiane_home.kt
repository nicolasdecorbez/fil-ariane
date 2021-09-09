package com.example.ardianethread.Activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Journey
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R


class fragment_ardiane_home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_ardiane_home, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState.apply {
            val username = view.findViewById<TextView>(R.id.ardiane_user)
            username.text = Global.Current.User.firstname
        }
    }
}