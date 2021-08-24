package com.example.ardianethread.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ardianethread.R
import com.example.ardianethread.Data.Tones

class ToneAdapter(private val toneList: ArrayList<Tones>):
    RecyclerView.Adapter<ToneAdapter.ToneViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToneViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tone_item, parent,false)
        return ToneViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToneViewHolder, position: Int) {
        val currentTone = toneList[position]
        holder.toneTitle.text = currentTone.title
    }
    override fun getItemCount(): Int {
        return  toneList.size
    }

    class ToneViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val toneTitle : TextView = itemView.findViewById(R.id.tone_title)
    }
}