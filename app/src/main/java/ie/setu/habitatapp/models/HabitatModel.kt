package ie.setu.habitatapp.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitatModel(var id: Long = 0,
                        var commonName: String = "",
                        var speciesDescription: String= "",
                        var habitatType: String="",
                        var image: Uri = Uri.EMPTY,
                        var lat: Double = 0.0,
                        var lng: Double = 0.0,
                        var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable