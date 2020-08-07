package vedder.xander.brewtracker.brew.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.lang.IllegalArgumentException

fun brewStatusFromString(string: String): BrewType {
    for (unit in BrewType.values()) {
        if (unit.stringValue.equals(string, ignoreCase = true)) {
            return unit;
        }
    }
    throw IllegalArgumentException("UnitType not found: $string")
}

@Parcelize
enum class BrewStatus(private val stringValue: String) : Parcelable {
    // TODO: String resource
    DOING("Doing"),
    FINISHED("Finished");

    override fun toString(): String {
        return stringValue
    }
}
