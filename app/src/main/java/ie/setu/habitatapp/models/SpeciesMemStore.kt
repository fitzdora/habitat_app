package ie.setu.habitatapp.models

import timber.log.Timber
import timber.log.Timber.Forest.i
class SpeciesMemStore : SpeciesStore {
    val speciesTypes = ArrayList<HabitatModel>()

    override fun findAll(): List<HabitatModel> {
        return speciesTypes
    }

    override fun create(speciesType: HabitatModel){
        speciesTypes.add(speciesType)
        logAll()
    }

    fun logAll() {
        speciesTypes.forEach{ i("${it}")}
    }

}