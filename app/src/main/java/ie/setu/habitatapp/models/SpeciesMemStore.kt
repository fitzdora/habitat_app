package ie.setu.habitatapp.models

class SpeciesMemStore : SpeciesStore {
    val speciesTypes = ArrayList<HabitatModel>()

    override fun findAll(): List<HabitatModel> {
        return speciesTypes
    }

    override fun create(speciesType: HabitatModel){
        speciesTypes.add(speciesType)
    }
}