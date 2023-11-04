package ie.setu.habitatapp.views.specieslist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.habitatapp.databinding.ActivitySpeciesListBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.R
import ie.setu.habitatapp.models.HabitatModel


class SpeciesListView : AppCompatActivity(), HabitatListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySpeciesListBinding
    lateinit var presenter: SpeciesListPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpeciesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        presenter = SpeciesListPresenter(this)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadSpecies()


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
        when (item.itemId) {
            R.id.item_add -> {
                presenter.doAddSpecies()
            }

            R.id.item_map -> {
                presenter.doShowSpeciesMap()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHabitatClick(speciesType: HabitatModel, pos: Int) {
        this.position = pos
        presenter.doEditSpecies(speciesType, this.position)
    }

    private fun loadSpecies(){
        binding.recyclerView.adapter = HabitatAdapter(presenter.getSpecies(), this)
        onRefresh()
    }

    fun onRefresh(){
        binding.recyclerView.adapter?.notifyItemRangeChanged(0, presenter.getSpecies().size)
    }

    fun onDelete(position: Int) {
        binding.recyclerView.adapter?.notifyItemRemoved(position)
    }
}

