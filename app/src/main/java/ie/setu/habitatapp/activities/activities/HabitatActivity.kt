package ie.setu.habitatapp.activities.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.R
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.helpers.showImagePicker
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.models.HabitatModel
import ie.setu.habitatapp.models.Location
import timber.log.Timber.Forest.i


class  HabitatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    var speciesType = HabitatModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edit = false
        //setContentView(R.layout.activity_habitat)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Habitat App Activity Started...")

        if (intent.hasExtra("species_edit")) {
            edit = true
            binding.btnAdd.setText(R.string.save_species)
            speciesType = intent.extras?.getParcelable("species_edit")!!
            binding.commonName.setText(speciesType.commonName)
            binding.speciesDescription.setText(speciesType.speciesDescription)
            binding.habitatType.setText(speciesType.habitatType)
            Picasso.get()
                .load(speciesType.image)
                .into(binding.speciesImage)
            if (speciesType.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_species_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            speciesType.commonName = binding.commonName.text.toString()
            speciesType.speciesDescription = binding.speciesDescription.text.toString()
            speciesType.habitatType = binding.habitatType.text.toString()
            if (speciesType.commonName.isEmpty()) {
                Snackbar
                    .make(it, R.string.enter_commonName, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.speciesTypes.update(speciesType.copy())
                } else {
                    app.speciesTypes.create(speciesType.copy())
                }
            }
            i("Add button pressed: $speciesType")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            i("Select image")
            showImagePicker(imageIntentLauncher, this)
        }

        binding.takeImage.setOnClickListener {
            i("Take image")
            /* todo */
        }

        binding.speciesLocation.setOnClickListener {
            i("Set Location Pressed")
            // set to Cobh
            val location = Location(51.8510, -8.29670, 15f)
            if (speciesType.zoom != 0f) {
                location.lat = speciesType.lat
                location.lng = speciesType.lng
                location.zoom = speciesType.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java).putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallBack()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_species, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_delete -> {
                app.speciesTypes.delete(speciesType)
                setResult(RESULT_OK)
                finish()
            }
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            when(result.resultCode){
                RESULT_OK -> {
                    if (result.data != null) {
                        i("Got Result ${result.data!!.data}")
                        val image = result.data!!.data!!
                        contentResolver.takePersistableUriPermission(image, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        speciesType.image = image
                        Picasso.get()
                            .load(speciesType.image)
                            .into(binding.speciesImage)
                        binding.chooseImage.setText(R.string.change_species_image)
                    } //end of if
                }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
        }

    private fun registerMapCallBack() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            speciesType.lat = location.lat
                            speciesType.lng = location.lng
                            speciesType.zoom = location.zoom
                        } // end of if
                    }

                    RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }

}