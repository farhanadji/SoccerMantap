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
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), EventView {
    private var nextEvent : MutableList<Event> = mutableListOf()
    protected lateinit var presenter: EventPresenter
    protected lateinit var adapter: EventAdapter
    private lateinit var rv: RecyclerView
    private var leagueId = "4328"

    companion object {
        fun newInstance(bundle: Bundle) : NextMatchFragment {
            val fragment = NextMatchFragment()
            fragment.arguments = bundle
            Log.v("ISI BUNDLE : ", bundle.getString("key"))
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_next_match, container, false)

        val gson = Gson()
        presenter = EventPresenter(this,gson)
        presenter.getNextMatch(leagueId)

        context?.let {
            adapter = EventAdapter(it, nextEvent) {
                startActivity<DetailEvent>("match" to it)
            }
        }
        rv = view.findViewById(R.id.nextMatchRV)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun showEventData(data: List<Event>) {
        nextEvent.clear()
        nextEvent.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
