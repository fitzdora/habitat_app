package ie.setu.habitatapp.models

import timber.log.Timber
import timber.log.Timber.Forest.i

var lastId = 0L
internal fun getId(): Long {
    return  lastId++
}
class SpeciesMemStore : SpeciesStore {
    val speciesTypes = ArrayList<HabitatModel>()

    override fun findAll(): List<HabitatModel> {
        return speciesTypes
    }

    override fun create(speciesType: HabitatModel){
        speciesType.id = getId()
        speciesTypes.add(speciesType)
        logAll()
    }

    override fun update(speciesType: HabitatModel){
        var foundSpecies: HabitatModel? = speciesTypes.find { s -> s.id == speciesType.id }
        if (foundSpecies != null){
            foundSpecies.commonName = speciesType.commonName
            foundSpecies.speciesDescription = speciesType.speciesDescription
            foundSpecies.habitatType = speciesType.habitatType
            foundSpecies.image = speciesType.image
            foundSpecies.lat = speciesType.lat
            foundSpecies.lng = speciesType.lng
            foundSpecies.zoom = speciesType.zoom
            logAll()
        }
    }

    fun logAll() {
        speciesTypes.forEach{ i("${it}")}
    }

}