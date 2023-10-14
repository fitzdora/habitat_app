package ie.setu.habitatapp.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        //setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        i("Habitat App Activity Started...")

        if(intent.hasExtra("species_edit")) {
            speciesType = intent.extras?.getParcelable("species_edit")!!
            binding.commonName.setText(speciesType.commonName)
            binding.speciesDescription.setText(speciesType.speciesDescription)
            binding.habitatType.setText(speciesType.habitatType)
        }

        binding.btnAdd.setOnClickListener(){
            speciesType.commonName = binding.commonName.text.toString()
            speciesType.speciesDescription = binding.speciesDescription.text.toString()
            speciesType.habitatType = binding.habitatType.text.toString()
            if(speciesType.commonName.isNotEmpty()) {
                app.speciesTypes.create(speciesType.copy())
                setResult(RESULT_OK)
                finish()
            }
            else {
                Snackbar
                    .make(it, "Please Enter a Common Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}