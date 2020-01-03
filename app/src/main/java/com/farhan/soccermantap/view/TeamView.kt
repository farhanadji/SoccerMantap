package com.farhan.soccermantap.view

import com.farhan.soccermantap.model.Team

interface TeamView {
    fun showTeamData(data: List<Team>)
}