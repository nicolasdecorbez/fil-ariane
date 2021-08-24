package com.example.ardianethread.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Journey
import com.example.ardianethread.R

class JourneyAdapter(private val journeyList: ArrayList<Journey>):
    RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyAdapter.JourneyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent,false)
        return JourneyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: JourneyViewHolder, position: Int) {
        val currentFriend = journeyList[position]
        holder.journeyUser.text = currentFriend.user
        holder.journeyTimer.text = currentFriend.timer
    }
    override fun getItemCount(): Int {
        return journeyList.size
    }

    class JourneyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val journeyUser : TextView = itemView.findViewById(R.id.friend_name)
        val journeyTimer : TextView = itemView.findViewById(R.id.friend_number)
    }
}