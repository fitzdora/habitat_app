package ie.setu.habitatapp.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.R
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel
import timber.log.Timber.Forest.i

class  HabitatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    var speciesType = HabitatModel()
    lateinit var app: MainApp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        //setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        i("Habitat App Activity Started...")

        if(intent.hasExtra("species_edit")) {
            edit = true
            binding.btnAdd.setText(R.string.save_species)
            speciesType = intent.extras?.getParcelable("species_edit")!!
            binding.commonName.setText(speciesType.commonName)
            binding.speciesDescription.setText(speciesType.speciesDescription)
            binding.habitatType.setText(speciesType.habitatType)
        }

        binding.btnAdd.setOnClickListener(){
            speciesType.commonName = binding.commonName.text.toString()
            speciesType.speciesDescription = binding.speciesDescription.text.toString()
            speciesType.habitatType = binding.habitatType.text.toString()
            if(speciesType.commonName.isEmpty()) {
                Snackbar
                    .make(it,R.string.enter_commonName, Snackbar.LENGTH_LONG)
                    .show()
            }
            else {
                if (edit) {
                    app.speciesTypes.update(speciesType.copy())
                } else {
                    app.speciesTypes.create(speciesType.copy())
                }
            }
                    setResult(RESULT_OK)
                    finish()
                }
            }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_species, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}