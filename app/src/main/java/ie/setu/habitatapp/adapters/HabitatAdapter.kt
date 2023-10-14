package ie.setu.habitatapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.habitatapp.databinding.CardSpeciesBinding
import ie.setu.habitatapp.models.HabitatModel
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

    class MainHolder(private val binding: CardSpeciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(species: HabitatModel) {
            binding.commonName.text = species.commonName
            binding.speciesDescription.text = species.speciesDescription
            binding.habitatType.text = species.habitatType
        }

    }
}