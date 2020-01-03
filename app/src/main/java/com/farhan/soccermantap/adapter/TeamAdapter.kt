package com.farhan.soccermantap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Team

class TeamAdapter(private val context: Context, private val items: List<Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder (
        LayoutInflater.from(context).inflate(R.layout.teams_list_item, parent, false)
    )


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View):  RecyclerView.ViewHolder(view){
        private val teamBadge = view.findViewById<ImageView>(R.id.team_badge_list)
        private val teamName = view.findViewById<TextView>(R.id.team_name_list)

        fun bindItem(item: Team, listener: (Team) -> Unit){
            teamName.text = item.teamName
            Glide.with(itemView.context).load(item.teamBadge).into(teamBadge)

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

}
