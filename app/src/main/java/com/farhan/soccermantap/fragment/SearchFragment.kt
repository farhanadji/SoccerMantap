package com.farhan.soccermantap.fragment


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.presenter.EventPresenter
import com.farhan.soccermantap.presenter.SearchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), com.farhan.soccermantap.view.SearchView {
    private lateinit var presenter : SearchPresenter
    private var events : MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var rv: RecyclerView
    private lateinit var sc: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_search, container, false)

        val gson = Gson()
        presenter = SearchPresenter(this,gson)
        context?.let {
            adapter = EventAdapter(it,events) {
                startActivity<DetailEvent>("match" to it)

            }
        }
        rv = view.findViewById(R.id.searchRV)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter


        sc = view.findViewById(R.id.searchMatch)
        sc.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(p0.toString().isNotEmpty()){
                    events.clear()
                    adapter.notifyDataSetChanged()
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
}
