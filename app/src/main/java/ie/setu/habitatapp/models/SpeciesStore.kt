package ie.setu.habitatapp.models

interface SpeciesStore {
    fun findAll(): List<HabitatModel>
    fun create(speciesType:HabitatModel)
    fun update(speciesType: HabitatModel)
    fun delete(speciesType: HabitatModel)
    fun findById(id:Long): HabitatModel?
}