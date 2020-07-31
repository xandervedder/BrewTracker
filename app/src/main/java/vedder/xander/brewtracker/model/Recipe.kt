package vedder.xander.brewtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class Recipe(
        var createdAt: LocalDate,
        var name: String,
        var type: String,
        var ingredients: MutableList<Ingredient>
) : Parcelable, Model
