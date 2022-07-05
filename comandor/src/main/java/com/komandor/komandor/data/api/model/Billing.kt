package com.komandor.komandor.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import javax.annotation.concurrent.Immutable

@Parcelize
@Immutable
data class Billing(
    @SerializedName("balance")
    val balance: Long? = null,
    @SerializedName("premium")
    val premium: Boolean? = null,
    @SerializedName("expiration")
    val expiration: Long? = null,
    @SerializedName("signatures")
    val signatures: Long? = null,
): Parcelable