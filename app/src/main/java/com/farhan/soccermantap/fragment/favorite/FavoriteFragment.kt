package com.farhan.soccermantap.fragment.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.database.database
import com.farhan.soccermantap.fragment.search.SearchEventFragment
import com.farhan.soccermantap.fragment.search.SearchTeamFragment
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Favorite
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_search.*
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager()
    }

    private fun viewPager() {
        viewpagerFavorite.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        FavoriteMatchFragment()
                    }
                    else -> {
                        FavoriteTeamFragment()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tabsFavorite, viewpagerFavorite) { tab, position ->
            tab.text = when(position) {
                0 -> "Match"
                else -> "Team"
            }
        }.attach()
    }
}
