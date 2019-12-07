package com.farhan.soccermantap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.EventDetail
import com.farhan.soccermantap.model.League

class FavoriteAdapter(private val context: Context, private val leagues: List<EventDetail>, private val listener: (EventDetail) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.event_list_item, parent, false
        )
    )

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val eventName = view.findViewById<TextView>(R.id.eventName)
        private val eventDate = view.findViewById<TextView>(R.id.eventDate)
        private val homeScore = view.findViewById<TextView>(R.id.homeScore)
        private val awayScore = view.findViewById<TextView>(R.id.awayScore)

        fun bindItem(items: EventDetail, listener: (EventDetail) -> Unit) {
            eventName.text = items.eventName
            eventDate.text = items.eventDate
            homeScore.text = items.homeScore
            awayScore.text = items.awayScore

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}