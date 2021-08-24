package com.example.ardianethread.Adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.Data.Messages
import com.example.ardianethread.Globals.Global
import com.example.ardianethread.R

class MessageAdapter(private val messageList: ArrayList<Messages>):
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdapter.MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent,false)
        return MessageAdapter.MessageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  messageList.size
    }

    override fun onBindViewHolder(holder: MessageAdapter.MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]
        holder.content.text = currentMessage.content
        var constraintSet = ConstraintSet()
        if(currentMessage.contact == Global.Current.User.firstname){
            constraintSet.clone(holder.container)
            constraintSet.connect(
                holder.content.id,
                ConstraintSet.END,
                holder.container.id,
                ConstraintSet.END,
                0
            )
            constraintSet.applyTo(holder.container)
        }
        else {
            constraintSet.clone(holder.container)
            constraintSet.connect(
                holder.content.id,
                ConstraintSet.START,
                holder.container.id,
                ConstraintSet.START,
                0
            )
            constraintSet.applyTo(holder.container)
        }
        }
    class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val container : ConstraintLayout = itemView.findViewById(R.id.message_holder)
        val content : TextView = itemView.findViewById(R.id.message_content)
    }
}