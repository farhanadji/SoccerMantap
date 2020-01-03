package com.farhan.soccermantap.fragment.detail_league


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailLeague
import com.farhan.soccermantap.activity.DetailTeam
import com.farhan.soccermantap.adapter.StandingAdapter
import com.farhan.soccermantap.adapter.TeamAdapter
import com.farhan.soccermantap.model.Team
import com.farhan.soccermantap.presenter.TeamPresenter
import com.farhan.soccermantap.view.TeamView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {
    private var teamData : MutableList<Team> = mutableListOf()
    protected lateinit var adapter: TeamAdapter
    private lateinit var presenter: TeamPresenter
    private lateinit var rv: RecyclerView
    private var leagueId = ""
    companion object {
        fun newInstance(bundle: Bundle): TeamFragment {
            val fragment = TeamFragment()
            fragment.arguments = bundle
            Log.v("ISI BUNDLE : ", bundle.getString("key"))
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            leagueId = it.getString("key", "4328")
        }
        val gson = Gson()
        presenter = TeamPresenter(this,gson)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_team, container, false)
        rv = view.findViewById(R.id.team_list)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        context?.let {
            adapter = TeamAdapter(it, teamData){
                startActivity<DetailTeam>("idTeam" to it.teamId)
            }
        }

        presenter.getTeam(leagueId)
        rv.adapter = adapter
    }

    override fun showTeamData(data: List<Team>) {
        teamData.clear()
        teamData.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
