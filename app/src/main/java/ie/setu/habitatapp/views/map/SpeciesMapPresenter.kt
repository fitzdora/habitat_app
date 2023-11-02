package ie.setu.habitatapp.views.map

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import ie.setu.habitatapp.main.MainApp

class SpeciesMapPresenter(val view: SpeciesMapView) {
    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun doPopulateMap(map:GoogleMap) {
        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(view)
        app.speciesTypes.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.commonName).position(loc)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val speciesType = app.speciesTypes.findById(tag)
        if(speciesType != null) view.showSpecies(speciesType)
    }
}