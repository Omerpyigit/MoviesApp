package com.example.moviesapp.Models

import android.os.Parcel
import android.os.Parcelable

data class SliderItems(
    var image: String = "",
    var name: String = "",  // Varsayılan değer ekle
    var genre: String = "",  // Varsayılan değer ekle
    var age: String = "",    // Varsayılan değer ekle
    var year: String = "",   // Varsayılan değer ekle
    var time: String = ""    // Varsayılan değer ekle
) : Parcelable {
    // Parametreli yapıcı
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",  // Null kontrolü
        parcel.readString() ?: "",    // Null kontrolü
        parcel.readString() ?: "",    // Null kontrolü
        parcel.readString() ?: "",    // Null kontrolü
        parcel.readString() ?: ""      // Null kontrolü
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(genre)
        parcel.writeString(age)
        parcel.writeString(year)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SliderItems> {
        override fun createFromParcel(parcel: Parcel): SliderItems {
            return SliderItems(parcel)
        }

        override fun newArray(size: Int): Array<SliderItems?> {
            return arrayOfNulls(size)
        }
    }
}
