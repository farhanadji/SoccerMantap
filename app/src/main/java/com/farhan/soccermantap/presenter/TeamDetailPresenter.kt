package com.farhan.soccermantap.presenter

import com.farhan.soccermantap.model.TeamResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class TeamDetailPresenter(val view: TeamView, val gson: Gson, val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getTeam(id: String){
        GlobalScope.launch(context.main){
            val teamData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getTeamDetail(id)).await(), TeamResponse::class.java)

            view.let {
                it.showTeamData(teamData.teams)
            }
        }
    }
}