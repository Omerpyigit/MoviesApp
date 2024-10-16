package com.example.moviesapp.Models

import android.os.Parcel
import android.os.Parcelable

data class FilmModel(
    var Title: String = "",
    var Description: String = "",
    var Poster: String = "",
    var Time: String = "",
    var Trailer: String = "",
    var Imdb: Int = 0,
    var Year: Int = 0,
    var Genre: ArrayList<String> = ArrayList(),
    var Casts: ArrayList<CastModel> = ArrayList()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readInt() ?: 0,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createTypedArrayList(CastModel.CREATOR) ?: ArrayList()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Title)
        parcel.writeString(Description)
        parcel.writeString(Poster)
        parcel.writeString(Time)
        parcel.writeString(Trailer)
        parcel.writeInt(Imdb)
        parcel.writeInt(Year)
        parcel.writeStringList(Genre)
        parcel.writeTypedList(Casts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilmModel> {
        override fun createFromParcel(parcel: Parcel): FilmModel {
            return FilmModel(parcel)
        }

        override fun newArray(size: Int): Array<FilmModel?> {
            return arrayOfNulls(size)
        }
    }
}
