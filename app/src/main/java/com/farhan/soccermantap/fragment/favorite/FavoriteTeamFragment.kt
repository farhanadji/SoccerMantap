package com.farhan.soccermantap.fragment.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.activity.DetailTeam
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.adapter.TeamAdapter
import com.farhan.soccermantap.database.database
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Favorite
import com.farhan.soccermantap.model.FavoriteTeam
import com.farhan.soccermantap.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {
    private var favTeam : MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_favorite_team, container, false)
        rv = view.findViewById(R.id.favoriteTeamRV)
        context?.let {
            adapter = TeamAdapter(it,favTeam){
                startActivity<DetailTeam>("idTeam" to it.teamId)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFavorite()
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
    }

    private fun showFavorite(){
        favTeam.clear()
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<Team>())
            favTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}
