package ie.setu.habitatapp.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.habitatapp.databinding.ActivitySpeciesListBinding
import ie.setu.habitatapp.databinding.CardSpeciesBinding
import ie.setu.habitatapp.main.MainApp
import ie.setu.habitatapp.R
import ie.setu.habitatapp.models.HabitatModel

class SpeciesListActivity : AppCompatActivity() {

    lateinit var app:MainApp
    private lateinit var binding: ActivitySpeciesListBinding
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
        binding.recyclerView.adapter = HabitatAdapter(app.speciesTypes)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

class HabitatAdapter constructor(private var speciesTypes: List<HabitatModel>) :
        RecyclerView.Adapter<HabitatAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardSpeciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val species = speciesTypes[holder.adapterPosition]
        holder.bind(species)
    }
    override fun getItemCount(): Int = speciesTypes.size

    class  MainHolder(private val binding: CardSpeciesBinding) :
            RecyclerView.ViewHolder(binding.root) {

                fun bind(species: HabitatModel) {
                    binding.commonName.text = species.commonName
                    binding.speciesDescription.text = species.speciesDescription
                    binding.habitatType.text = species.habitatType
                }

    }            }