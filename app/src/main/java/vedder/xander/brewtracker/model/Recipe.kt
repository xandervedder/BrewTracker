package vedder.xander.brewtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
// TODO: maybe a way to go from ConfigData to Recipe and vice-versa?
data class Recipe(
        var createdAt: LocalDate,
        var name: String,
        var type: String,
        var ingredient: MutableList<Ingredient>
) : Parcelable
