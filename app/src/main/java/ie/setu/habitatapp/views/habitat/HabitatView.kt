package ie.setu.habitatapp.views.habitat

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.google.android.material.snackbar.Snackbar
import ie.setu.habitatapp.R
import ie.setu.habitatapp.databinding.ActivityHabitatBinding
import ie.setu.habitatapp.models.HabitatModel
import timber.log.Timber.Forest.i


class  HabitatView : AppCompatActivity() {

    private lateinit var binding: ActivityHabitatBinding
    private lateinit var presenter: HabitatPresenter
    var speciesType = HabitatModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHabitatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        presenter = HabitatPresenter(this)

        binding.chooseImage.setOnClickListener {
            presenter.cacheHabitat(
                binding.commonName.text.toString(),
                binding.speciesDescription.text.toString(),
                binding.habitatType.text.toString()
            )
            presenter.doSelectImage()
        }

        binding.takeImage.setOnClickListener {
            i("Take image")
            /* todo */
        }

        binding.speciesLocation.setOnClickListener {
            presenter.cacheHabitat(
                binding.commonName.text.toString(),
                binding.speciesDescription.text.toString(),
                binding.habitatType.text.toString()
            )
            presenter.doSetLocation()
        }

    /*    binding.btnAdd.setOnClickListener {
            if (binding.commonName.text.toString().isEmpty()) {
                Snackbar.make(binding.root, R.string.enter_commonName, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                presenter.doAddOrSave(
                    binding.commonName.text.toString(),
                    binding.speciesDescription.text.toString(),
                    binding.habitatType.text.toString()
                )
            }
        }*/
    }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_species, menu)
            val deleteMenu: MenuItem = menu.findItem(R.id.item_delete)
            deleteMenu.isVisible = presenter.edit
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_add -> {
                    if (binding.commonName.text.toString().isEmpty()) {
                        Snackbar.make(binding.root, R.string.enter_commonName, Snackbar.LENGTH_LONG)
                            .show()
                    } else {
                        presenter.doAddOrSave(
                            binding.commonName.text.toString(),
                            binding.speciesDescription.text.toString(),
                            binding.habitatType.text.toString()
                        )
                    }
                }
                R.id.item_delete -> { presenter.doDelete() }
                R.id.item_cancel -> { presenter.doCancel() }
        }
            return super.onOptionsItemSelected(item)
        }

    fun showSpecies(speciesType: HabitatModel) {
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

    fun updateImage(image: Uri){
        i("image updated")
        Picasso.get()
            .load(image)
            .into(binding.speciesImage)
        binding.chooseImage.setText(R.string.change_species_image)
    }
}