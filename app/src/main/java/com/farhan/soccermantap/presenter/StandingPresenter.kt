package com.farhan.soccermantap.presenter

import com.farhan.soccermantap.model.StandingResponse
import com.farhan.soccermantap.model.TeamResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.StandingView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class StandingPresenter(val view: StandingView, val gson: Gson, val context: CoroutineContextProvider = CoroutineContextProvider()){
    fun getStanding(id: String){
        GlobalScope.launch (context.main){
            val standingData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getStanding(id)).await(), StandingResponse::class.java)

            view.let {
                it.showData(standingData.table)
            }
        }
    }
}