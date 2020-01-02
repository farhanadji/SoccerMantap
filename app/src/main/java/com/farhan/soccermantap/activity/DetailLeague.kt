package com.farhan.soccermantap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.fragment.detail_league.StandingFragment
import com.farhan.soccermantap.fragment.detail_league.TeamFragment
import com.farhan.soccermantap.fragment.match.NextMatchFragment
import com.farhan.soccermantap.fragment.match.PreviousMatchFragment
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.League
import com.farhan.soccermantap.model.Standing
import com.farhan.soccermantap.presenter.DetailEventPresenter
import com.farhan.soccermantap.presenter.DetailLeaguePresenter
import com.farhan.soccermantap.view.DetailLeagueView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_league.*
import kotlinx.android.synthetic.main.fragment_event.*

class DetailLeague : AppCompatActivity(), DetailLeagueView {

    private lateinit var presenter: DetailLeaguePresenter
    private var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        val league = intent.extras?.getString("idLeague")
        bundle.putString("key", league)
        Log.v("IDNYA : ", league)

        val gson = Gson()
        presenter = DetailLeaguePresenter(this, gson)
        presenter.getDetailLeague(league!!)

        viewPager()
    }

    override fun showLeague(league: List<League>) {
        leagueNameDetail.text = league[0].name
        leagueDescDetail.text = league[0].description
        Glide.with(this).load(league[0].badge).into(leagueBadgeDetail)
    }

    private fun viewPager() {
        viewpagerLeague.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        StandingFragment.newInstance(bundle)
                    }
                    else -> {
                        TeamFragment()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 2
            }
        }

        TabLayoutMediator(tabsLeague, viewpagerLeague) { tab, position ->
            tab.text = when(position) {
                0 -> "Standing"
                else -> "Team"
            }
        }.attach()
    }
}
