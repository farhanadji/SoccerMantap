package com.farhan.soccermantap.network

import com.farhan.soccermantap.BuildConfig

object SportDBAPI {
    fun getPrevMatch(id: String): String {
        return BuildConfig.BASE_URL + "eventspastleague.php?id=$id"
    }

    fun getNextMatch(id: String): String {
        return BuildConfig.BASE_URL + "eventsnextleague.php?id=$id"
    }

    fun getTeamDetail(id: String): String {
        return BuildConfig.BASE_URL + "lookupteam.php?id=$id"
    }

    fun getLeagueDetail(id: String): String {
        return BuildConfig.BASE_URL + "lookupleague.php?id=$id"
    }

    fun getSearchQuery(param: String): String {
        return BuildConfig.BASE_URL + "searchevents.php?e=$param"
    }

    fun getStanding(id: String): String {
        return BuildConfig.BASE_URL + "lookuptable.php?l=$id"
    }

    fun getTeamList(id: String) : String {
        return BuildConfig.BASE_URL + "lookup_all_teams.php?id=$id"
    }

    fun getSearchTeam(param: String): String {
        return BuildConfig.BASE_URL + "searchteams.php?t=$param"
    }
}