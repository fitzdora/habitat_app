package ie.setu.habitatapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitatModel(var id: Long = 0,
                        var commonName: String = "",
                        var speciesDescription: String= "",
                        var habitatType: String="") : Parcelable