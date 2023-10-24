package ie.setu.habitatapp.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.setu.habitatapp.R

fun showImagePicker(intentLauncher: ActivityResultLauncher<Intent>){
    var chooseFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseFile.type = "image/*"
    chooseFile = Intent.createChooser(chooseFile, R.string.select_species_image.toString())
    intentLauncher.launch(chooseFile)
}

fun takeImage(intentLauncher: ActivityResultLauncher<Intent>){
    // TODO: set camera to take image 
}
