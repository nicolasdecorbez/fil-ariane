package com.example.ardianethread.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Users
import com.example.ardianethread.R

class SetArdianeAdapter(private val friendList: ArrayList<Users>):
    RecyclerView.Adapter<SetArdianeAdapter.ContactViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetArdianeAdapter.ContactViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent,false)
            return ContactViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
            val currentFriend = friendList[position]
            holder.friendName.text = currentFriend.firstname
            holder.friendNumber.text = currentFriend.phone
        }
        override fun getItemCount(): Int {
            return friendList.size
        }

        class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val friendName : TextView = itemView.findViewById(R.id.friend_name)
            val friendNumber : TextView = itemView.findViewById(R.id.friend_number)
            var itemName = itemView.findViewById<TextView>(R.id.friend_name)
            var itemNumber = itemView.findViewById<TextView>(R.id.friend_number)

            init {
                itemView.setOnClickListener{
                    itemName.setTextColor(Color.WHITE)
                    itemNumber.setTextColor(Color.WHITE)
                }
            }
        }
    }