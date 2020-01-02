package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.League
import com.farhan.soccermantap.model.Standing
import com.farhan.soccermantap.model.Team

interface DetailLeagueView  {
    fun showLeague(league: List<League>)
}