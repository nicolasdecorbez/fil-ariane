package com.example.ardianethread.Adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Users
import com.example.ardianethread.Globals.Global
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
            var itemColor = itemName.currentTextColor

            init {
//                var arrayId : ArrayList<Int> = arrayListOf()
                itemView.setOnClickListener {
                    if (itemName.currentTextColor == Color.WHITE) {

                        itemName.setTextColor(itemColor)

                        for (i in 0 until Global.TempFriendUser.UsersArray.size)
                        {
                            if (Global.TempFriendUser.UsersArray[i].firstname == itemName.text)
                            {
                                Global.TempFriendUser.UsersArray[i].id?.let { it1 ->  Global.CurrentJourneys.arrayId.remove(it1) }
                                Log.e("GLOBAL", Global.CurrentJourneys.arrayId.toString())
                            }
                        }
                    } else {
                        itemName.setTextColor(Color.WHITE)
                        for (i in 0 until Global.TempFriendUser.UsersArray.size)
                        {
                            if (Global.TempFriendUser.UsersArray[i].firstname == itemName.text) {
                                Global.TempFriendUser.UsersArray[i].id?.let { it1 ->
                                    Global.CurrentJourneys.arrayId.add(it1)
                                }
                                Log.e("GLOBAL", Global.CurrentJourneys.arrayId.toString())
                            }
                        }
                    }
                    if (itemNumber.currentTextColor == Color.WHITE) {
                        itemNumber.setTextColor(itemColor)

                } else {
                    itemNumber.setTextColor(Color.WHITE)
                    }
                }
            }
        }
    }