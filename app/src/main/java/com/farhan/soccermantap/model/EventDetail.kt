package com.farhan.soccermantap.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDetail (
    val eventId: String? = null,
    val eventName: String? = null,
    val eventDate: String? = null,
    val homeId: String? = null,
    val homeTeam: String? = null,
    val homeScore: String? = null,
    val homeGoal: String? = null,
    val homeGoalkeeper: String? = null,
    val homeDefense: String? = null,
    val homeMidfield: String? = null,
    val homeForward: String? = null,
    val homeSubstitutes: String? = null,
    val awayId: String? = null,
    val awayTeam:  String? = null,
    val awayScore: String? = null,
    val awayGoal: String? = null,
    val awayGoalkeeper: String? = null,
    val awayDefense: String? = null,
    val awayMidfield: String? = null,
    val awayForward: String? = null,
    val awaySubstitutes: String? = null
) : Parcelable