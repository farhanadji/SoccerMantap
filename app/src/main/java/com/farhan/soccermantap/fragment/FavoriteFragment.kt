package com.farhan.soccermantap.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.database.database
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {
    private var favEvents : MutableList<Event> = mutableListOf()
    private lateinit var adapter: EventAdapter
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_favorite, container, false)
        rv = view.findViewById(R.id.favoriteRV)
        context?.let {
            adapter = EventAdapter(it,favEvents){
                startActivity<DetailEvent>("match" to it)
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
        favEvents.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Event>())
            favEvents.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
