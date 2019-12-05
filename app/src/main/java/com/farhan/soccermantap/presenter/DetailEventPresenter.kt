package com.farhan.soccermantap.presenter

import com.farhan.soccermantap.activity.DetailEvent
import com.farhan.soccermantap.model.TeamResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.EventDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailEventPresenter(val view: EventDetailView, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getDetailTeam(homeId: String?, awayId: String?){
        GlobalScope.launch (context.main){
            val homeData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getTeamDetail(homeId.toString())).await(), TeamResponse::class.java)
            val awayData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getTeamDetail(awayId.toString())).await(), TeamResponse::class.java)

            view.let {
                it.showTeam(homeData.team, awayData.team)
            }
        }
    }
}