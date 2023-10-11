package ie.setu.habitatapp.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.R
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.models.HabitatModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class  HabitatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    var speciesType = HabitatModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Habitat Activity Started...")

        binding.btnAdd.setOnClickListener(){
            speciesType.commonName = binding.commonName.text.toString()
            if(speciesType.commonName.isNotEmpty()){
                i("add Button Pressed: $speciesType.commonName")
            }
            else {
                Snackbar
                    .make(it, "Please Enter a Common Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}