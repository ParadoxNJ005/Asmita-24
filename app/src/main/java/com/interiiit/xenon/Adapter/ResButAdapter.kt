package com.interiiit.xenon.Adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.interiiit.xenon.DataClass.Score.MatchDetails
import com.interiiit.xenon.R

class ResButAdapter(val butt:List<MatchDetails>,
                    private val itemClickListener: com.interiiit.xenon.Fragment.results
    )
    : RecyclerView.Adapter<ResButAdapter.ViewHolder>(){

    private var selectedItemPosition: Int = 0
    private val uniqueDates: List<String> = butt.map { it.date }.distinct()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val day: AppCompatButton = itemView.findViewById(R.id.day)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_butt , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uniqueDates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = uniqueDates[position]
        holder.day.text = date
        holder.itemView.setOnClickListener {
            holder.day.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E9BD3E"))
            itemClickListener.onResClick(butt.first { it.date == date })
            setSelectedPosition(position)
        }

        if (selectedItemPosition == position) {
            holder.day.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E9BD3E"))
            itemClickListener.onResClick(butt[position])
        } else {
            holder.day.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
        }

        holder.itemView.setOnClickListener {
            setSelectedPosition(position)
            itemClickListener.onResClick(butt[position])
        }

    }

    fun setSelectedPosition(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }
}