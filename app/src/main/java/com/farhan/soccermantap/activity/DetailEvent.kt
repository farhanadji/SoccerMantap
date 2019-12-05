package com.farhan.soccermantap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Team
import com.farhan.soccermantap.network.sportDB
import com.farhan.soccermantap.presenter.DetailEventPresenter
import com.farhan.soccermantap.view.EventDetailView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_event.*

class DetailEvent : AppCompatActivity(), EventDetailView {

    private lateinit var presenter: DetailEventPresenter
    private lateinit var event: Event
    companion object {
        var leagueId: String? = "0"
        var homeId: String? = "0"
        var awayId: String? = "0"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        val gson = Gson()
        presenter = DetailEventPresenter(this, gson)

        event = intent.extras?.getParcelable<Event>("match")!!
        leagueId = event!!.eventId
        homeId = event.homeId
        awayId = event.awayId

        loadDataEvent(event)
        presenter.getDetailTeam(homeId, awayId)


    }

    override fun showTeam(home: List<Team>, away: List<Team>) {
        Glide.with(this).load(home[0].teamBadge).into(homeTeamBadge)
        Glide.with(this).load(away[0].teamBadge).into(awayTeamBadge)
    }

    fun loadDataEvent(data: Event){
        homeDefense.text = data.homeDefense
        awayDefense.text = data.awayDefense
        homeForward.text = data.homeForward
        awayForward.text = data.awayForward
        homeGoalkeeper.text = data.homeGoalkeeper
        awayGoalKeeper.text = data.awayGoalkeeper
        homeGoals.text = data.homeGoal
        awayGoals.text = data.awayGoal
        homeMidfield.text = data.homeMidfield
        awayMidfield.text = data.awayMidfield
        homeScoreDetail.text = data.homeScore.toString()
        awayScoreDetail.text = data.awayScore.toString()
    }
}
