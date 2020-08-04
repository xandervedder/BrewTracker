package vedder.xander.brewtracker.ingredient.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class UnitType : Parcelable {
    MILLIGRAM,
    GRAM,
    KILOGRAM,
}
