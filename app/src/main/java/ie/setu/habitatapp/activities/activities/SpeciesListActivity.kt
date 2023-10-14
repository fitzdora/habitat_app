package ie.setu.habitatapp.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.R

class SpeciesListActivity : AppCompatActivity() {

    lateinit var app:MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species_list)
        app = application as MainApp
    }
}