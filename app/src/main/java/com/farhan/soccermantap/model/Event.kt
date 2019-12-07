package com.farhan.soccermantap.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event (
    @SerializedName("idEvent")
    val eventId: String? = null,

    @SerializedName("strEvent")
    val eventName: String? = null,

    @SerializedName("dateEvent")
    val eventDate: String? = null,

    @SerializedName("idHomeTeam")
    val homeId: String? = null,

    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,

    @SerializedName("intHomeScore")
    val homeScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    val homeGoal: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    val homeDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val homeMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    val homeForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val homeSubstitutes: String? = null,

    @SerializedName("idAwayTeam")
    val awayId: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeam:  String? = null,

    @SerializedName("intAwayScore")
    val awayScore: String? = null,

    @SerializedName("strAwayGoalDetails")
    val awayGoal: String? = null,

    @SerializedName("strAwayLineupGoalKeeper")
    val awayGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    val awayDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val awayMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    val awayForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val awaySubstitutes: String? = null
) : Parcelable {
    override fun toString(): String {
        return "Event(eventId=$eventId, eventName=$eventName, eventDate=$eventDate, homeId=$homeId, homeTeam=$homeTeam, homeScore=$homeScore, homeGoal=$homeGoal, homeGoalKeeper=$homeGoalkeeper, homeDefense=$homeDefense, homeMidfield=$homeMidfield, homeForward=$homeForward, homeSubstitutes=$homeSubstitutes, awayId=$awayId, awayTeam=$awayTeam, awayScore=$awayScore, awayGoal=$awayGoal, awayGoalkeeper=$awayGoalkeeper, awayDefense=$awayDefense, awayMidfield=$awayMidfield, awayForward=$awayForward, awaySubstitutes=$awaySubstitutes"
    }
}