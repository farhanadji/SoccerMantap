package com.farhan.soccermantap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.League
import com.farhan.soccermantap.presenter.DetailEventPresenter
import com.farhan.soccermantap.presenter.DetailLeaguePresenter
import com.farhan.soccermantap.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeague : AppCompatActivity(), DetailLeagueView {
    private lateinit var presenter: DetailLeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        val league =  intent.extras?.getString("idLeague")

        Log.v("IDNYA : ", league)
        val gson = Gson()
        presenter = DetailLeaguePresenter(this,gson)
        presenter.getDetailLeague(league!!)

    }
    override fun showLeague(league: List<League>) {
        leagueNameDetail.text = league[0].name
        leagueDescDetail.text = league[0].description
        Glide.with(this).load(league[0].badge).into(leagueBadgeDetail)
    }
}
