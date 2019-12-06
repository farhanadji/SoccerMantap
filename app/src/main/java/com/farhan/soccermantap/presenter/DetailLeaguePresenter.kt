package com.farhan.soccermantap.presenter

import com.farhan.soccermantap.activity.DetailLeague
import com.farhan.soccermantap.model.LeaguesResponse
import com.farhan.soccermantap.model.TeamResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.DetailLeagueView
import com.farhan.soccermantap.view.EventDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailLeaguePresenter(val view: DetailLeagueView, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getDetailLeague(id: String){
        GlobalScope.launch (context.main){
            val leagueData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getLeagueDetail(id)).await(), LeaguesResponse::class.java)

            view.let {
                it.showLeague(leagueData.leagues)
            }
        }
    }
}