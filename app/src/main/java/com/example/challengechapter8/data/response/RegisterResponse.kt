package com.example.challengechapter8.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
) : Parcelable