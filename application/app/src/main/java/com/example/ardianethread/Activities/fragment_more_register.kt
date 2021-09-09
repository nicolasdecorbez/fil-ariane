package com.example.ardianethread.Activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import com.example.ardianethread.R

class fragment_more_register : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var v : View  = inflater.inflate(R.layout.fragment_more_register, container, false)
        var more_info_button : Button = v.findViewById(R.id.accept_register_em)
        var no_info_button : Button = v.findViewById(R.id.refuse_register_em)

        more_info_button.setOnClickListener {
            val intent = Intent(this.context, ProfileActivity::class.java)
            startActivity(intent)
        }
        no_info_button.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}