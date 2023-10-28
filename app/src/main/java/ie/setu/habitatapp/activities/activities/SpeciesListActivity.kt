package ie.setu.habitatapp.activities.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.habitatapp.databinding.ActivitySpeciesListBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.R
import ie.setu.habitatapp.adapters.HabitatAdapter
import ie.setu.habitatapp.adapters.HabitatListener
import ie.setu.habitatapp.models.HabitatModel


class SpeciesListActivity : AppCompatActivity(), HabitatListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySpeciesListBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_species_list)
        binding = ActivitySpeciesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        //binding.recyclerView.adapter = HabitatAdapter(app.speciesTypes)
        binding.recyclerView.adapter = HabitatAdapter(app.speciesTypes.findAll(),this)

        /*   to do to test seperatly to work

   val speciesSpinner: Spinner = findViewById(R.id.spinner_no_of_species_seen_array)
      ArrayAdapter.createFromResource(this, R.array.no_of_species_seen_array, android.R.layout.simple_spinner_item).also { adapter ->
          adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
          speciesSpinner.adapter = adapter
      }*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_add -> {
                val launcherIntent = Intent(this, HabitatActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.speciesTypes.findAll().size)
        }
    }

    override fun onHabitatClick(species: HabitatModel, pos : Int) {
        val launcherIntent = Intent(this, HabitatActivity::class.java)
        launcherIntent.putExtra("species_edit", species)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private  val getClickResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK) {
            (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.speciesTypes.findAll().size)
        }
        else //Deleting (link to onOptionsItemSelected in HabitatActivity
            if (it.resultCode == 99) (binding.recyclerView.adapter)?.notifyItemRemoved(position)
    }
}

