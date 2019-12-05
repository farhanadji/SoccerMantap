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
import com.farhan.soccermantap.model.League

class LeagueAdapter(private val context: Context, private val items: List<League>, private val listener: (League) -> Unit) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.leagues_list_item, parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val leagueName = view.findViewById<TextView>(R.id.leagueNameHome)
        private val leagueBadge = view.findViewById<ImageView>(R.id.leagueBadgeHome)

        fun bindItem(item: League, listener: (League) -> Unit) {
            leagueName.text = item.name
            Glide.with(itemView.context).load(item.badge).into(leagueBadge)

            itemView.setOnClickListener {
                listener(item)
            }
        }

    }
}
