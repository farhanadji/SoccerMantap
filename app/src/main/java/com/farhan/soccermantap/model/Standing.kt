package com.farhan.soccermantap.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Standing(
    @SerializedName("name")
    var teamName: String?,

    @SerializedName("teamid")
    var teamId: String?,

    @SerializedName("played")
    var played: String?,

    @SerializedName("win")
    var win: String?,

    @SerializedName("draw")
    var draw: String?,

    @SerializedName("loss")
    var loss: String?,

    @SerializedName("total")
    var total: String?
) : Parcelable