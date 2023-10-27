package ie.setu.habitatapp.helpers

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.setu.habitatapp.R

fun showImagePicker(intentLauncher: ActivityResultLauncher<Intent>, context: Context){
   var imagePickerTargetIntent = Intent()
    imagePickerTargetIntent.action = Intent.ACTION_OPEN_DOCUMENT
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
    imagePickerTargetIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    imagePickerTargetIntent.type = "image/*"
    imagePickerTargetIntent = Intent.createChooser(imagePickerTargetIntent, context.getString(R.string.select_species_image))
    intentLauncher.launch(imagePickerTargetIntent)
}

fun takeImage(intentLauncher: ActivityResultLauncher<Intent>){
    // TODO: set camera to take image 
}
