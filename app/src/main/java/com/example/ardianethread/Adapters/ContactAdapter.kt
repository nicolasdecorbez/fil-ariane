package com.example.ardianethread.Adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.contentValuesOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Activities.fragment_friend_add
import com.example.ardianethread.Data.Users
import com.example.ardianethread.R

class ContactAdapter(private val friendList: ArrayList<Users>):
RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int){

        }
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent,false)
        return ContactViewHolder(itemView,mListener)
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentFriend = friendList[position]
        holder.friendName.text = currentFriend.firstname
        holder.friendNumber.text = currentFriend.phone
    }
        override fun getItemCount(): Int {
            return friendList.size
        }

        class ContactViewHolder(itemView : View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

            val friendName : TextView = itemView.findViewById(R.id.friend_name)
            val friendNumber : TextView = itemView.findViewById(R.id.friend_number)
            var itemName = itemView.findViewById<TextView>(R.id.friend_name)
            var itemNumber = itemView.findViewById<TextView>(R.id.friend_number)

            init {
                itemView.setOnClickListener{
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }