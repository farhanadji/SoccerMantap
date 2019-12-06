package com.farhan.soccermantap.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailLeague
import com.farhan.soccermantap.adapter.LeagueAdapter
import com.farhan.soccermantap.model.League
import com.farhan.soccermantap.model.LeagueHome
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {
    private var item : MutableList<LeagueHome> = mutableListOf()
    private lateinit var rv: RecyclerView
    private lateinit var adapter: LeagueAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View =  inflater.inflate(R.layout.fragment_league, container, false)
        loadData()

        context?.let {
            adapter = LeagueAdapter(it, item) {
                startActivity<DetailLeague>("idLeague" to it.id)
            }
        }

        rv = view.findViewById(R.id.leagueRV)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)
        return view
    }

    private fun loadData(){
        val leagueId = resources.getStringArray(R.array.league_id)
        val leagueTitle = resources.getStringArray(R.array.league_name)
        val leagueBadge = resources.obtainTypedArray(R.array.league_badge)
        val leagueDescription = resources.getStringArray(R.array.league_description)
        item.clear()
        for(i in leagueTitle.indices){
            item.add(LeagueHome(leagueId[i], leagueTitle[i],leagueBadge.getResourceId(i,0),leagueDescription[i]))
        }
        leagueBadge.recycle()
    }



}
