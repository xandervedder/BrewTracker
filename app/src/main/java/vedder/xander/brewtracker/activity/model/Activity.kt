package vedder.xander.brewtracker.activity.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Activity(var type: String) : Parcelable
