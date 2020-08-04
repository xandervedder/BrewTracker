package vedder.xander.brewtracker.brew.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class BrewStatus : Parcelable {
    DOING,
    FINISHED,
}
