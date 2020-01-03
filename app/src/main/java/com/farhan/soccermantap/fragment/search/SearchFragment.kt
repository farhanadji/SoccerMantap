package com.farhan.soccermantap.fragment.search


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.farhan.soccermantap.R
import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.adapter.EventAdapter
import com.farhan.soccermantap.fragment.detail_league.StandingFragment
import com.farhan.soccermantap.fragment.detail_league.TeamFragment
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.presenter.SearchPresenter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager()
    }

    private fun viewPager() {
        viewpagerSearch.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        SearchEventFragment()
                    }
                    else -> {
                        SearchTeamFragment()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tabsSearch, viewpagerSearch) { tab, position ->
            tab.text = when(position) {
                0 -> "Match"
                else -> "Team"
            }
        }.attach()
    }
}
