package vedder.xander.brewtracker.ingredient.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
        var name: String,
        var amount: String,
        var unit: UnitType
): Parcelable
