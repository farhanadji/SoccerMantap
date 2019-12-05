package com.farhan.soccermantap.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event (
    @SerializedName("idEvent")
    val eventId: String?,

    @SerializedName("strEvent")
    val eventName: String?,

    @SerializedName("strHomeTeam")
    val homeTeam: String?,

    @SerializedName("strAwayTeam")
    val awayTeam:  String?,

    @SerializedName("intHomeScore")
    val homeScore: Int?,

    @SerializedName("intAwayScore")
    val awayScore: Int?,

    @SerializedName("strHomeGoalDetails")
    val homeGoal: String?,

    @SerializedName("strAwayGoalDetails")
    val awayGoal: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalkeeper: String?,

    @SerializedName("strAwayLineupGoalKeeper")
    val awayGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    val homeDefense: String?,

    @SerializedName("strAwayLineupDefense")
    val awayDefense: String?,

    @SerializedName("strHomeLineupMidfield")
    val homeMidfield: String?,

    @SerializedName("strAwayLineupMidfield")
    val awayMidfield: String?,

    @SerializedName("strHomeLineupForward")
    val homeForward: String?,

    @SerializedName("strAwayLineupForward")
    val awayForward: String?,

    @SerializedName("strHomeLineupSubstitutes")
    val homeSubstitutes: String?,

    @SerializedName("strAwayLineupSubstitutes")
    val awaySubstitutes: String?,

    @SerializedName("idHomeTeam")
    val homeId: String,

    @SerializedName("idAwayTeam")
    val awayId: String,

    @SerializedName("strDate")
    val eventDate: String?
) : Parcelable