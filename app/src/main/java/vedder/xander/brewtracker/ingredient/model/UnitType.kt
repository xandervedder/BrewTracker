package vedder.xander.brewtracker.ingredient.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.IllegalArgumentException

fun unitTypeFromString(string: String): UnitType {
    for (unit in UnitType.values()) {
        if (unit.stringValue.equals(string, ignoreCase = true)) {
            return unit;
        }
    }
    throw IllegalArgumentException("UnitType not found: $string")
}

@Parcelize
enum class UnitType(val stringValue: String) : Parcelable {
    MILLIGRAM("Milligram"),
    GRAM("Gram"),
    KILOGRAM("Kilogram");

    override fun toString(): String {
        return stringValue
    }
}
