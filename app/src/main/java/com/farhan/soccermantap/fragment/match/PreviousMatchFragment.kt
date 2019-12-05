package com.farhan.soccermantap.fragment.match


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.network.sportDB
import com.farhan.soccermantap.presenter.EventPresenter
import com.farhan.soccermantap.view.EventView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment : Fragment(), EventView {
    private var prevMatch : MutableList<Event> = mutableListOf()
    private lateinit var presenter: EventPresenter
    private lateinit var adapter: EventAdapter
    private lateinit var rv: RecyclerView
    private var leagueId = ""

    companion object {
        fun newInstance(bundle: Bundle) : PreviousMatchFragment {
            val fragment = PreviousMatchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            leagueId = it.getString("key", "4328")
        }

        val gson = Gson()
        presenter = EventPresenter(this,gson)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_previous_match, container, false)
        rv = view.findViewById(R.id.prevMatchRV)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        context?.let {
            adapter = EventAdapter(it, prevMatch) {
                startActivity<DetailEvent>("match" to it)
            }
        }
        presenter.getPrevMatch(leagueId)
        rv.adapter = adapter
    }

    override fun showEventData(data: List<Event>) {
        prevMatch.clear()
        prevMatch.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
