package ie.setu.habitatapp.views.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Picasso
import ie.setu.habitatapp.databinding.ActivitySpeciesMapsBinding
import ie.setu.habitatapp.databinding.ContentSpeciesMapsBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel

class SpeciesMapView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    private lateinit var binding: ActivitySpeciesMapsBinding
    private lateinit var contentBinding: ContentSpeciesMapsBinding
    lateinit var app: MainApp
    lateinit var presenter: SpeciesMapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpeciesMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        app = application as MainApp

        presenter = SpeciesMapPresenter(this)

        contentBinding = ContentSpeciesMapsBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)
        contentBinding.mapView.getMapAsync {
            presenter.doPopulateMap(it)
        }

    }

    fun showSpecies(speciesType: HabitatModel) {
        contentBinding.currentTitle.text = speciesType.commonName
        contentBinding.currentDescription.text = speciesType.speciesDescription
        Picasso.get()
            .load(speciesType.image)
            .into(contentBinding.currentImage)

    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.doMarkerSelected(marker)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }

}