package ie.setu.habitatapp.views.specieslist

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ie.setu.habitatapp.views.habitat.HabitatView
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel
import ie.setu.habitatapp.views.map.SpeciesMapView

class SpeciesListPresenter(val view: SpeciesListView) {

    var app: MainApp
    private lateinit var refreshIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
    private var position: Int = 0

    init {
        app = view.application as MainApp
        registerMapCallback()
        registerRefreshCallback()
    }

    fun getSpecies() = app.speciesTypes.findAll()

    fun doAddSpecies() {
        val launcherIntent = Intent(view, HabitatView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doEditSpecies(speciesType: HabitatModel, pos: Int) {
        val launcherIntent = Intent(view, HabitatView::class.java)
        launcherIntent.putExtra("species_edit", speciesType)
        position = pos
        refreshIntentLauncher.launch(launcherIntent)
    }

    fun doShowSpeciesMap() {
        val launcherIntent = Intent(view, SpeciesMapView::class.java)
        refreshIntentLauncher.launch(launcherIntent)
    }

    private fun registerRefreshCallback() {
         refreshIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
         {
             if (it.resultCode == Activity.RESULT_OK) view.onRefresh()
             else // Deleting
                 if (it.resultCode == 99) view.onDelete(position)
         }
    }

    private fun registerMapCallback() {
        mapIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { }
    }
}