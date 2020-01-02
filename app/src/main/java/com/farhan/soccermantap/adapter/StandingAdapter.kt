package com.farhan.soccermantap.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Standing
import kotlinx.android.synthetic.main.standings_list_item.view.*

class StandingAdapter(private val context: Context, private val item: List<Standing>, private val listener: (Standing) -> Unit) : RecyclerView.Adapter<StandingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder (
        LayoutInflater.from(context).inflate(R.layout.standings_list_item, parent, false)
    )

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener, position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName = view.findViewById<TextView>(R.id.club_name_standing)
        private val played = view.findViewById<TextView>(R.id.played_standing)
        private val win = view.findViewById<TextView>(R.id.win_standing)
        private val loss = view.findViewById<TextView>(R.id.loss_standing)
        private val draw = view.findViewById<TextView>(R.id.draw_standing)
        private val total = view.findViewById<TextView>(R.id.total_standing)
        private val rank = view.findViewById<TextView>(R.id.rank_standing)

        fun bindItem(item: Standing, listener: (Standing) -> Unit, num: Int){
            rank.text = (num+1).toString()
            teamName.text = item.teamName
            played.text = item.played
            win.text = item.win
            loss.text = item.loss
            draw.text = item.draw
            total.text = item.draw

            itemView.setOnClickListener {
                listener(item)
            }
        }

    }
}