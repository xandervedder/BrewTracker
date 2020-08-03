package vedder.xander.brewtracker.recipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import vedder.xander.brewtracker.ingredient.model.Ingredient
import java.time.LocalDate

@Parcelize
data class Recipe(
        var createdAt: LocalDate,
        var name: String,
        var type: String,
        var ingredients: MutableList<Ingredient>
) : Parcelable
