package ie.setu.habitatapp.views.editlocation

import ie.setu.habitatapp.models.Location

class EditLocationPresenter(val view: EditLocationView) {

    var location = Location()

    init {
        location = view.intent.extras?.getParcelable
    }
}