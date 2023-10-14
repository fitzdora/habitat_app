package ie.setu.habitatapp.models

interface SpeciesStore {
    fun findAll(): List<HabitatModel>
    fun create(speciesType:HabitatModel)
}