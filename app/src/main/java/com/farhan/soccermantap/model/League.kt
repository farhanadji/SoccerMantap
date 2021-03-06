package com.farhan.soccermantap.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName("idLeague")
    var id: String?,

    @SerializedName("strLeague")
    var name: String?,

    @SerializedName("strBadge")
    var badge: String?,

    @SerializedName("strDescriptionEN")
    var description: String?
) : Parcelable