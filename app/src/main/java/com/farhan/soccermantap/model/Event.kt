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

    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    val awayTeam:  String? = null,

    @SerializedName("intHomeScore")
    val homeScore: String? = null,

    @SerializedName("intAwayScore")
    val awayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    val homeGoal: String? = null,

    @SerializedName("strAwayGoalDetails")
    val awayGoal: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalkeeper: String? = null,

    @SerializedName("strAwayLineupGoalKeeper")
    val awayGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    val homeDefense: String? = null,

    @SerializedName("strAwayLineupDefense")
    val awayDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    val homeMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield")
    val awayMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    val homeForward: String? = null,

    @SerializedName("strAwayLineupForward")
    val awayForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    val homeSubstitutes: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    val awaySubstitutes: String? = null,

    @SerializedName("idHomeTeam")
    val homeId: String? = null,

    @SerializedName("idAwayTeam")
    val awayId: String? = null,

    @SerializedName("dateEvent")
    val eventDate: String? = null,

    @SerializedName("strSport")
    val sportType: String? = null
) : Parcelable