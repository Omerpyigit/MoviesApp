package com.example.moviesapp.Models

import android.os.Parcel
import android.os.Parcelable

data class CastModel(
    var PicUrl: String = "",
    var actor: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(PicUrl)
        parcel.writeString(actor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CastModel> {
        override fun createFromParcel(parcel: Parcel): CastModel {
            return CastModel(parcel)
        }

        override fun newArray(size: Int): Array<CastModel?> {
            return arrayOfNulls(size)
        }
    }
}
