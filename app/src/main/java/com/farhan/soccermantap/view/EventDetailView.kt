package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.Team

interface EventDetailView  {
    fun showTeam(home: List<Team>, away: List<Team>)
}