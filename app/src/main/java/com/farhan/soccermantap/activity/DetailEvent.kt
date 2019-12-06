package com.farhan.soccermantap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Team
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
        homeTeamName.text = data.homeTeam
        awayTeamName.text = data.awayTeam
        homeDefense.text = data.homeDefense?.replace(';','\n')
        awayDefense.text = data.awayDefense?.replace(';','\n')
        homeForward.text = data.homeForward?.replace(';','\n')
        awayForward.text = data.awayForward?.replace(';','\n')
        homeGoalkeeper.text = data.homeGoalkeeper?.replace(';','\n')
        awayGoalKeeper.text = data.awayGoalkeeper?.replace(';','\n')
        homeGoals.text = data.homeGoal?.replace(';','\n')
        awayGoals.text = data.awayGoal?.replace(';','\n')
        homeMidfield.text = data.homeMidfield?.replace(';','\n')
        awayMidfield.text = data.awayMidfield?.replace(';','\n')
        if(data.homeScore.isNullOrEmpty().not()){
        homeScoreDetail.text = data.homeScore.toString()
        }else{
            homeScoreDetail.text = "-"
        }

        if(data.awayScore.isNullOrEmpty().not()){
            awayScoreDetail.text = data.awayScore.toString()
        }else{
            awayScoreDetail.text = "-"
        }
    }
}
