package com.farhan.soccermantap.presenter

import com.farhan.soccermantap.model.EventResponse
import com.farhan.soccermantap.model.SearchResponse
import com.farhan.soccermantap.model.TeamResponse
import com.farhan.soccermantap.network.ApiRepository
import com.farhan.soccermantap.network.SportDBAPI
import com.farhan.soccermantap.util.CoroutineContextProvider
import com.farhan.soccermantap.view.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(private val view: SearchView, private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getSearchData(param: String){
        GlobalScope.launch(context.main){
            val searchData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getSearchQuery(param)).await(), SearchResponse::class.java)
            if(searchData.event != null){
                view.let {
                    it.showSearchResult(searchData.event.filter { it.sportType.equals("Soccer") })
                }
            }else{
                view.let {
                    it.showEmpty()
                }
            }
        }
    }

    fun getSearchTeamData(param: String){
        GlobalScope.launch(context.main){
            val searchTeamData = gson.fromJson(ApiRepository().doRequestAsync(SportDBAPI.getSearchTeam(param)).await(), TeamResponse::class.java)
            if(searchTeamData.teams != null){
                view.let {
                    it.showSearchTeam(searchTeamData.teams.filter { it.teamSportType.equals("Soccer") })
                }
            }else{
                view.let {
                    it.showEmpty()
                }
            }
        }
    }
}