package ie.setu.habitatapp.activities.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ie.setu.habitatapp.databinding.ActivitySpeciesMapsBinding

class SpeciesMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpeciesMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpeciesMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

    }
}