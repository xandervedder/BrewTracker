package vedder.xander.brewtracker.adapter.holder

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

interface ViewHolderFactory {

    fun assemble(@NonNull view: View): RecyclerView.ViewHolder
}