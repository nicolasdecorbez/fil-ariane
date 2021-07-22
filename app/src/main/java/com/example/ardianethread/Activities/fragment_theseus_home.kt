package com.example.ardianethread.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R



class fragment_theseus_home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        val v = inflater.inflate(R.layout.fragment_theseus_home, container, false)

        val start_button = v.findViewById<Button>(R.id.start_Button)

        start_button.setOnClickListener{
            val start_fragment = fragment_ardiane_home()
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment_theseus_home,start_fragment)
            transaction.commit()
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState.apply {

            val username = view.findViewById<TextView>(R.id.theseus_user)
            username.text = Global.Current.User.firstname
        }
    }




}