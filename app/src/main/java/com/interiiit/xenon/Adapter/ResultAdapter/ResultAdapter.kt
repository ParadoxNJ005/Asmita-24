package com.interiiit.xenon.Adapter.ResultAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.interiiit.xenon.DataClass.Score.MatchDetails
import com.interiiit.xenon.R

class ResultAdapter (
    var sch: List<MatchDetails>,
    val logo:Map<String,Int>,
    private val resClickListener: com.interiiit.xenon.Fragment.Home,
    val boolean: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     fun updateData(newList: List<MatchDetails>) {
        sch = newList
        notifyDataSetChanged()
    }

    private val CRICKET = 1
    private val FOOBALL = 2
    private val ATHELETE = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CRICKET -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_cricket, parent, false)
                CricketViewHolder(view)
            }
            FOOBALL -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_football, parent, false)
                FootballViewHolder(view)
            }
            ATHELETE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.layout_atheletes, parent, false)
                AtheleteViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val matchDetails = sch[position]
        return if (matchDetails.matchType == "cricket") {
            CRICKET
        }
        else if(matchDetails.matchType=="football"){
            FOOBALL
        }
        else if (matchDetails.matchType=="athlete") {
            ATHELETE
        }
        else{
            throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = sch.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val scc = sch[position]

        when (viewType) {
            CRICKET -> {
                val crickHolder = holder as CricketViewHolder
                crickHolder.name.text = scc.matchName
                crickHolder.date.text = scc.date
                crickHolder.clg1.text = scc.clgName1
                crickHolder.clg2.text = scc.clgName2
                crickHolder.sc1.text = scc.score1
                crickHolder.sc2.text = scc.score2
                crickHolder.ov1.text = "ov: ${scc.over1}"
                crickHolder.grpStage.text = scc.time
                crickHolder.ov2.text = "ov: ${scc.over2}"

                Glide.with(crickHolder.itemView.context)
                    .load(logo[scc.clgName1])
                    .error(R.drawable.group)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(crickHolder.img1)
                Glide.with(crickHolder.itemView.context)
                    .load(logo[scc.clgName2])
                    .error(R.drawable.group)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(crickHolder.img2)
            }
            FOOBALL -> {
                val footHolder = holder as FootballViewHolder
                footHolder.name.text = scc.matchName
                footHolder.date.text = scc.date
                footHolder.clg1.text = scc.clgName1
                footHolder.clg2.text = scc.clgName2
                footHolder.point.text = scc.point
                footHolder.grpStage.text = scc.time
                Glide.with(footHolder.itemView.context)
                    .load(logo[scc.clgName1])
                    .error(R.drawable.group)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(footHolder.img1)
                Glide.with(footHolder.itemView.context)
                    .load(logo[scc.clgName2])
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.group)
                    .into(footHolder.img2)
            }
            ATHELETE -> {
                val atHolder = holder as AtheleteViewHolder
                atHolder.name.text = scc.matchName
                atHolder.date.text = scc.date
                atHolder.play1.text = scc.play1
                atHolder.play2.text = scc.play2
                atHolder.play3.text = scc.play3
                atHolder.grpStage.text = scc.time
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
        if(boolean){
            holder.itemView.setOnClickListener {
                resClickListener.onResClick()
            }
        }

    }
    class CricketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.matchName)
        val date: TextView = itemView.findViewById(R.id.date)
        val grpStage: TextView = itemView.findViewById(R.id.time)
        val clg1: TextView = itemView.findViewById(R.id.clgName1)
        val clg2: TextView = itemView.findViewById(R.id.clgName2)
        val sc1: TextView = itemView.findViewById(R.id.poi1)
        val sc2: TextView = itemView.findViewById(R.id.poi2)
        val ov1: TextView = itemView.findViewById(R.id.ov1)
        val ov2: TextView = itemView.findViewById(R.id.ov2)
        val img1: ImageView = itemView.findViewById(R.id.clgImg1)
        val img2: ImageView = itemView.findViewById(R.id.clgImg2)
    }
    class FootballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.matchName)
        val date: TextView = itemView.findViewById(R.id.date)
        val grpStage: TextView = itemView.findViewById(R.id.time)
        val clg1: TextView = itemView.findViewById(R.id.clgName1)
        val clg2: TextView = itemView.findViewById(R.id.clgName2)
        val point: TextView = itemView.findViewById(R.id.points)
        val img1: ImageView = itemView.findViewById(R.id.clgImg1)
        val img2: ImageView = itemView.findViewById(R.id.clgImg2)
    }
    class AtheleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.matchName)
        val date: TextView = itemView.findViewById(R.id.date)
        val grpStage: TextView = itemView.findViewById(R.id.time)
        val play1: TextView = itemView.findViewById(R.id.play1)
        val play2: TextView = itemView.findViewById(R.id.play2)
        val play3: TextView = itemView.findViewById(R.id.play3)
    }
}