package com.example.ardianethread.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.R
import com.example.ardianethread.Tones

class ToneAdapter(private val toneList: ArrayList<Tones>):
    RecyclerView.Adapter<ToneAdapter.ToneViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToneAdapter.ToneViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tone_item, parent,false)
        return ToneViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToneAdapter.ToneViewHolder, position: Int) {
        val currentTone = toneList[position]
        holder.toneTile.text = currentTone.title
    }

    override fun getItemCount(): Int {
        return  toneList.size
    }

    class ToneViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val toneTile : TextView = itemView.findViewById(R.id.tone_title)
    }
}