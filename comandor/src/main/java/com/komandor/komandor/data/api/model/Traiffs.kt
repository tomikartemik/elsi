package com.komandor.komandor.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import javax.annotation.concurrent.Immutable

@Parcelize
@Immutable
data class Traiffs(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("cost")
    val cost: Long? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("titleDescription")
    val titleDescription: String? = null,

    @SerializedName("price")
    val price: String? = null,

    @SerializedName("priceDescription")
    val priceDescription: String? = null,

    @SerializedName("type")
    val type: Int? = null,

    @SerializedName("order")
    val order: Int? = null,
): Parcelable