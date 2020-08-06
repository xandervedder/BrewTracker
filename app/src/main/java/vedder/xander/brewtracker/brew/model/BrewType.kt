package vedder.xander.brewtracker.brew.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.IllegalArgumentException

fun brewTypeFromString(string: String): BrewType {
    for (unit in BrewType.values()) {
        if (unit.stringValue.equals(string, ignoreCase = true)) {
            return unit;
        }
    }
    throw IllegalArgumentException("UnitType not found: $string")
}

@Parcelize
enum class BrewType(val stringValue: String) : Parcelable {
    BEER("Beer"),
    WINE("Wine"),
    MEAD("Mead");

    override fun toString(): String {
        return stringValue
    }
}
