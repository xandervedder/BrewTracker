package vedder.xander.brewtracker.brew.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import vedder.xander.brewtracker.recipe.model.Recipe
import java.time.LocalDate

@Parcelize
data class Brew(
        var dateCreated: LocalDate,
        var recipe: Recipe,
        var status: BrewStatus
) : Parcelable
