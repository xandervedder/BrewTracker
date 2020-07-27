package vedder.xander.brewtracker.adapter.holder

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.adapter.GenericAdapter

interface ViewHolderFactory {
    fun assemble(@NonNull view: View): GenericAdapter.GenericViewHolder
}