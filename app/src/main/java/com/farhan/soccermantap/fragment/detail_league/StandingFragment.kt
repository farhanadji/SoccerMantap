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
import com.farhan.soccermantap.adapter.StandingAdapter
import com.farhan.soccermantap.model.Standing
import com.farhan.soccermantap.presenter.StandingPresenter
import com.farhan.soccermantap.view.StandingView
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment(), StandingView {

    private var standingData : MutableList<Standing> = mutableListOf()
    protected lateinit var adapter: StandingAdapter
    private lateinit var presenter: StandingPresenter
    private lateinit var rv: RecyclerView
    private var leagueId = ""
    companion object {
        fun newInstance(bundle: Bundle): StandingFragment {
            val fragment = StandingFragment()
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
        presenter = StandingPresenter(this,gson)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_standing, container, false)
        rv = view.findViewById(R.id.list_standings)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        context?.let {
            adapter = StandingAdapter(it, standingData){
                val toast = Toast.makeText(context, it.teamName, Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        presenter.getStanding(leagueId)
        rv.adapter = adapter
    }

    override fun showData(data: List<Standing>) {
        standingData.clear()
        standingData.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
