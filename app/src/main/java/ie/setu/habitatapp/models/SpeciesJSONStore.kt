package ie.setu.habitatapp.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.habitatapp.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

const val JSON_FILE = "species.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<HabitatModel>>() {}.type

fun generateRandomId(): Long{
    return Random().nextLong()
}

class SpeciesJSONStore(private val context: Context) : SpeciesStore {

    var speciesTypes = mutableListOf<HabitatModel>()

    init{
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HabitatModel> {
        logAll()
        return speciesTypes
    }

    override fun create(speciesType: HabitatModel) {
        speciesType.id = generateRandomId()
        speciesTypes.add(speciesType)
        serialize()
    }

    override fun update(speciesType: HabitatModel) {
        val speciesList = findAll() as ArrayList<HabitatModel>
        var foundSpecies: HabitatModel? = speciesTypes.find { s -> s.id == speciesType.id }
        if (foundSpecies != null) {
            foundSpecies.commonName = speciesType.commonName
            foundSpecies.speciesDescription = speciesType.speciesDescription
            foundSpecies.habitatType = speciesType.habitatType
            foundSpecies.image = speciesType.image
            foundSpecies.lat = speciesType.lat
            foundSpecies.lng = speciesType.lng
            foundSpecies.zoom = speciesType.zoom
        }
        serialize()
    }

    override fun delete(speciesType: HabitatModel) {
        speciesTypes.remove(speciesType)
        serialize()
    }

    override fun findById(id: Long): HabitatModel? {
        val foundSpecies: HabitatModel? = speciesTypes.find { it.id == id }
        return foundSpecies
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(speciesTypes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        speciesTypes = gsonBuilder.fromJson(jsonString, listType)

    }

    private fun logAll(){
        speciesTypes.forEach{ Timber.i("$it")}
    }
}

class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}