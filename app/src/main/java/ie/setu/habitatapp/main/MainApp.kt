package ie.setu.habitatapp.main

import android.app.Application
import ie.setu.habitatapp.models.HabitatModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class MainApp : Application() {

    val speciesTypes = ArrayList<HabitatModel>()
    override fun onCreate() {

        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Habitat App Started")
    }
}