package ie.setu.habitatapp.views.habitat

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ie.setu.habitatapp.views.editlocation.EditLocationView
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.helpers.showImagePicker
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel
import ie.setu.habitatapp.models.Location
import timber.log.Timber


class HabitatPresenter(private val view: HabitatView) {

    var speciesType = HabitatModel()
    var app: MainApp = view.application as MainApp
    var binding: ActivityHabitatBinding = ActivityHabitatBinding.inflate(view.layoutInflater)
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
    var edit = false

    init {
        if (view.intent.hasExtra("species_edit")){
            edit = true
            speciesType = view.intent.extras?.getParcelable("species_edit")!!
            view.showSpecies(speciesType)
        }
        registerImagePickerCallback()
        registerMapCallback()
    }

    fun doAddOrSave(title: String, description: String, habitatType: String){
        speciesType.commonName = title
        speciesType.speciesDescription = description
        speciesType.habitatType = habitatType
        if (edit){
            app.speciesTypes.update(speciesType)
        } else {
            app.speciesTypes.create(speciesType)
        }
        view.setResult(RESULT_OK)
        view.finish()
    }

    fun doCancel(){
        view.finish()
    }

    fun doDelete(){
        view.setResult(99)
        app.speciesTypes.delete(speciesType)
        view.finish()
    }

    fun doSelectImage(){
        showImagePicker(imageIntentLauncher, view)
    }

    fun doSetLocation(){
        val location = Location(51.8510, -8.29670, 15f)
        if (speciesType.zoom != 0f) {
            location.lat = speciesType.lat
            location.lng = speciesType.lng
            location.zoom = speciesType.zoom
        }
        val launcherIntent = Intent(view, EditLocationView::class.java).putExtra("location", location)
        mapIntentLauncher.launch(launcherIntent)
    }

    fun cacheHabitat(title: String, description: String, habitatType: String) {
        speciesType.commonName = title
        speciesType.speciesDescription = description
        speciesType.habitatType = habitatType
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher = view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            when(result.resultCode){
                AppCompatActivity.RESULT_OK -> {
                    if (result.data != null) {
                        Timber.i("Got Result ${result.data!!.data}")
                        speciesType.image = result.data!!.data!!
                        view.contentResolver.takePersistableUriPermission(speciesType.image, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        view.updateImage(speciesType.image)
                    } //end of if
                }
                AppCompatActivity.RESULT_CANCELED -> { } else -> { }
            }
        }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            view.registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {
                            Timber.i("Got Location ${result.data.toString()}")
                            val location =
                                result.data!!.extras?.getParcelable<Location>("location")!!
                            Timber.i("Location == $location")
                            speciesType.lat = location.lat
                            speciesType.lng = location.lng
                            speciesType.zoom = location.zoom
                        } // end of if
                    }

                    AppCompatActivity.RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }

}