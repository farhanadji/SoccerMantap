package com.farhan.soccermantap.model

import com.google.gson.annotations.SerializedName

class Favorite{
    companion object {
        const val TABLE_FAVORITE = "TABLE_FAVORITE"
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_DATE = "EVENT_DATE"
        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_TEAM_SCORE = "HOME_TEAM_SCORE"
        const val HOME_TEAM_GOAL = "HOME_TEAM_GOAL"
        const val HOME_TEAM_GOALKEEPER = "HOME_TEAM_GOALKEEPER"
        const val HOME_TEAM_DEFENSE = "HOME_TEAM_DEFENSE"
        const val HOME_TEAM_MIDFIELD = "HOME_TEAM_MIDFIELD"
        const val HOME_TEAM_FORWARD = "HOME_TEAM_FORWARD"
        const val HOME_TEAM_SUBSTITUTES = "HOME_TEAM_SUBTITUTES"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_SCORE = "AWAY_TEAM_SCORE"
        const val AWAY_TEAM_GOAL = "AWAY_TEAM_GOAL"
        const val AWAY_TEAM_GOALKEEPER = "AWAY_TEAM_GOALKEEPER"
        const val AWAY_TEAM_DEFENSE = "AWAY_TEAM_DEFENSE"
        const val AWAY_TEAM_MIDFIELD = "AWAY_TEAM_MIDFIELD"
        const val AWAY_TEAM_FORWARD = "AWAY_TEAM_FORWARD"
        const val AWAY_TEAM_SUBSTITUTES = "AWAY_TEAM_SUBTITUTES"
        const val SPORT_TYPE = "SPORT_TYPE"
    }
}