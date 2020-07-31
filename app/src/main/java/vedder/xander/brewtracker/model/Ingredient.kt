package vedder.xander.brewtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(val name: String, val amount: String, val unit: String): Parcelable, Model
