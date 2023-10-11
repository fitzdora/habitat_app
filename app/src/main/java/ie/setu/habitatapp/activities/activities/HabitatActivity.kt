package ie.setu.habitatapp.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.R
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class  HabitatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    var speciesType = HabitatModel()
    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Habitat App Activity Started...")
        binding.btnAdd.setOnClickListener(){
            speciesType.commonName = binding.commonName.text.toString()
            speciesType.speciesDescription = binding.speciesDescription.text.toString()
            speciesType.habitatType = binding.habitatType.text.toString()
            if(speciesType.commonName.isNotEmpty()) {
                app.speciesTypes.add(speciesType.copy())
                i("add Button Pressed: $speciesType.commonName")
                for (i in app.speciesTypes.indices) {
                    i("SpeciesType[$i]:${this.app.speciesTypes[i]}")
                }
            }
            else {
                Snackbar
                    .make(it, "Please Enter a Common Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}