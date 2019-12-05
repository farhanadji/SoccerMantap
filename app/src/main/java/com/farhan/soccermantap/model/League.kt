package com.farhan.soccermantap.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var id: String?,
    var name: String?,
    var badge: Int?,
    var description: String?
) : Parcelable