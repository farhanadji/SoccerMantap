package com.farhan.soccermantap.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.farhan.soccermantap.R
import com.farhan.soccermantap.adapter.TeamAdapter
import com.farhan.soccermantap.database.database
import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Favorite
import com.farhan.soccermantap.model.FavoriteTeam
import com.farhan.soccermantap.model.Team
import com.farhan.soccermantap.presenter.DetailLeaguePresenter
import com.farhan.soccermantap.presenter.TeamDetailPresenter
import com.farhan.soccermantap.presenter.TeamPresenter
import com.farhan.soccermantap.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeam : AppCompatActivity(), TeamView {
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu? = null
    private lateinit var team: Team
    private var isFavorite: Boolean = false
    companion object {
        var id: String? = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val idTeam = intent.extras?.getString("idTeam")
        id = idTeam

        favoriteState()

        val gson = Gson()
        presenter = TeamDetailPresenter(this, gson)
        presenter.getTeam(idTeam!!)
    }

    override fun showTeamData(data: List<Team>) {
        team_name_detail.text = data[0].teamName
        team_description_detail.text = data[0].teamDescription
        Glide.with(this).load(data[0].teamBadge).into(team_badge_detail)
        team = data.get(0)

        team.teamId = data.get(0).teamId
        team.teamName = data.get(0).teamName
        team.teamBadge = data.get(0).teamBadge
        team.teamDescription = data.get(0).teamDescription
        team.teamSportType = data.get(0).teamSportType
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
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge,
                    FavoriteTeam.TEAM_DESCRIPTION to team.teamDescription,
                    FavoriteTeam.TEAM_SPORT_TYPE to team.teamSportType
                )
            }
            val toast = Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT)
            toast.show()
        }catch (e: SQLiteConstraintException){
            val toast = Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})", "id" to id.toString())
            }

            val toast = Toast.makeText(this,"Removed from favorite", Toast.LENGTH_SHORT)
            toast.show()

        } catch (e: SQLiteConstraintException) {
            val toast = Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
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
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("(TEAM_ID = {id})", "id" to id!!)
            val favorite = result.parseList(classParser<Team>())
            if(!favorite.isEmpty()) isFavorite = true
        }
    }
}
