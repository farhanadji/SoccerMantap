package com.farhan.soccermantap.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueHome(
    var id: String?,
    var name: String?,
    var badge: Int?,
    var description: String?
) : Parcelable