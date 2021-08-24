package com.example.ardianethread.Activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Adapters.ContactAdapter
import com.example.ardianethread.Adapters.FriendAdapter
import com.example.ardianethread.Adapters.SetArdianeAdapter
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class fragment_theseus_ardiane : Fragment() {

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
        var v = inflater.inflate(R.layout.fragment_theseus_ardiane, container, false)

        friendView = v.findViewById<RecyclerView>(R.id.ardiane_view)
        friendView.layoutManager = LinearLayoutManager(this.context)
        friendView.setHasFixedSize(true)

        friendName = arrayOf(
            "Nico",
            "Kevin",
            "Lucas"
        )

        friendNumber = arrayOf(
            "06.00.00.00.01",
            "06.00.00.00.02",
            "06.00.00.00.03"
        )

        friendList = arrayListOf<Users>()
        getFriendData()

        var adapter = SetArdianeAdapter(friendList)
        friendView.adapter = adapter

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

}