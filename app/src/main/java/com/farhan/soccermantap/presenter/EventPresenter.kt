package com.farhan.soccermantap.presenter

import android.util.Log
import com.farhan.soccermantap.fragment.match.NextMatchFragment
import com.farhan.soccermantap.model.EventResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.EventView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class EventPresenter (private var view: EventView, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getPrevMatch(id: String){
        GlobalScope.launch(context.main) {
            val prevMatch = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getPrevMatch(id)).await(), EventResponse::class.java)
            view.let {
                it.showEventData(prevMatch.events)
            }
        }
    }

    fun getNextMatch(id: String){
        GlobalScope.launch(context.main){
            val nextMatch = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getNextMatch(id)).await(), EventResponse::class.java)

            view.let {
                it.showEventData(nextMatch.events)
            }
        }
    }
}