package com.farhan.soccermantap.fragment.search


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
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Team
import com.farhan.soccermantap.presenter.SearchPresenter
import com.farhan.soccermantap.view.SearchView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class SearchEventFragment : Fragment(), SearchView {
    private lateinit var presenter : SearchPresenter
    private var events : MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var rv: RecyclerView
    private lateinit var sc: android.widget.SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_search_event, container, false)

        val gson = Gson()
        presenter = SearchPresenter(this,gson)
        context?.let {
            adapter = EventAdapter(it,events) {
                startActivity<DetailEvent>("match" to it)

            }
        }

        rv = view.findViewById(R.id.searchMatchRV)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter


        sc = view.findViewById(R.id.searchMatch)
        sc.setOnQueryTextListener(object: android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(p0.toString().isNotEmpty()){
                    events.clear()
                    presenter.getSearchData(p0.toString())
                    Log.v("Search: ", p0.toString())
                    return true
                }else{
                    return false
                }
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return view

    }

    override fun showSearchResult(search: List<Event>) {
        events.clear()
        events.addAll(search)
        adapter.notifyDataSetChanged()
    }

    override fun showSearchTeam(searchTeam: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmpty() {
        val toast = Toast.makeText(context, "Not found!", Toast.LENGTH_SHORT)
        toast.show()
    }


}
