package com.farhan.soccermantap.activity

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Team
import com.farhan.soccermantap.presenter.DetailEventPresenter
import com.farhan.soccermantap.view.EventDetailView
import com.google.gson.Gson
import com.farhan.soccermantap.database.database
import com.farhan.soccermantap.model.Favorite
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailEvent : AppCompatActivity(), EventDetailView {

    private lateinit var presenter: DetailEventPresenter
    private lateinit var event: Event
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    companion object {
        var eventId: String? = "0"
        var homeId: String? = "0"
        var awayId: String? = "0"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        event = intent.extras?.getParcelable<Event>("match")!!
        eventId = event.eventId
        homeId = event.homeId
        awayId = event.awayId

        favoriteState()

        val gson = Gson()
        presenter = DetailEventPresenter(this, gson)


        Log.d("IS FAVORITE? ", isFavorite.toString())

        loadDataEvent(event)
        presenter.getDetailTeam(homeId, awayId)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_event,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_to_favorite -> {
                if(isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to event.eventId,
                    Favorite.EVENT_NAME to event.eventName,
                    Favorite.EVENT_DATE to event.eventDate,
                    Favorite.HOME_TEAM_ID to event.homeId,
                    Favorite.HOME_TEAM_NAME to event.homeTeam,
                    Favorite.HOME_TEAM_SCORE to event.homeScore,
                    Favorite.HOME_TEAM_GOAL to event.homeGoal,
                    Favorite.HOME_TEAM_GOALKEEPER to event.homeGoalkeeper,
                    Favorite.HOME_TEAM_DEFENSE to event.homeDefense,
                    Favorite.HOME_TEAM_MIDFIELD to event.homeMidfield,
                    Favorite.HOME_TEAM_FORWARD to event.homeForward,
                    Favorite.HOME_TEAM_SUBSTITUTES to event.homeSubstitutes,
                    Favorite.AWAY_TEAM_ID to event.awayId,
                    Favorite.AWAY_TEAM_NAME to event.awayTeam,
                    Favorite.AWAY_TEAM_SCORE to event.awayScore,
                    Favorite.AWAY_TEAM_GOAL to event.awayGoal,
                    Favorite.AWAY_TEAM_GOALKEEPER to event.awayGoalkeeper,
                    Favorite.AWAY_TEAM_DEFENSE to event.awayDefense,
                    Favorite.AWAY_TEAM_MIDFIELD to event.awayMidfield,
                    Favorite.AWAY_TEAM_FORWARD to event.awayForward,
                    Favorite.AWAY_TEAM_SUBSTITUTES to event.awaySubstitutes,
                    Favorite.SPORT_TYPE to event.sportType
                )
            }

            val toast = Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT)
            toast.show()
        } catch (e: SQLiteConstraintException){
            val toast = Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to eventId.toString())
            }

            val toast = Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT)
            toast.show()

        } catch (e: SQLiteConstraintException) {
            val toast = Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
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

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})", "id" to eventId!!)
            val favorite = result.parseList(classParser<Event>())
            if(!favorite.isEmpty()) isFavorite = true
        }
    }
}
