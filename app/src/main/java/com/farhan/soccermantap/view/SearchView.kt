package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.Event
import com.farhan.soccermantap.model.Team

interface SearchView {
    fun showSearchResult(search: List<Event>)
    fun showSearchTeam(searchTeam: List<Team>)
    fun showEmpty()
}