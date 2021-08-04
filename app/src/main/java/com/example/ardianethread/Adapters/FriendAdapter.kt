package com.example.ardianethread.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Users
import com.example.ardianethread.R

class FriendAdapter(private val friendList: ArrayList<Users>):
    RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.FriendViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent,false)
        return FriendViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val currentFriend = friendList[position]
        holder.friendName.text = currentFriend.firstname
        holder.friendNumber.text = currentFriend.phone
    }
        override fun getItemCount(): Int {
            return  friendList.size
        }

        class FriendViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val friendName : TextView = itemView.findViewById(R.id.friend_name)
            val friendNumber : TextView = itemView.findViewById(R.id.friend_number)
        }

    }