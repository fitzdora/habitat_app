package ie.setu.habitatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import timber.log.Timber
import timber.log.Timber.Forest.i

class HabitatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Habitat Activity Started...")

        binding.btnAdd.setOnClickListener(){
            val commonName = binding.commonName.text.toString()
            if(commonName.isNotEmpty()){
                i("add Button Pressed: $commonName")
            }
            else {
                Snackbar
                    .make(it, "Please Enter a Common Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}