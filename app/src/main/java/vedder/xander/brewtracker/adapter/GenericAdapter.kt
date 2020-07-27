package vedder.xander.brewtracker.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory
import vedder.xander.brewtracker.config.ConfigData
import vedder.xander.brewtracker.factory.ViewFactory
import vedder.xander.brewtracker.pattern.ViewTypePattern

class GenericAdapter(
        private val dataset: List<ConfigData>?,
        private val viewFactories: List<ViewFactory<out View>>,
        private val viewHolderFactories: List<ViewHolderFactory>,
        private val pattern: ViewTypePattern?,
        private val listener: EventListener,
        customSize: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val usingDataset: Boolean = dataset != null
    private val customDatasetSize: Int = customSize!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = viewFactories[viewType].assemble(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val holder = viewHolderFactories[viewType].assemble(view)
        if (holder.shouldHaveListener()) {
            holder.addListener(listener)
        }
        return viewHolderFactories[viewType].assemble(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (usingDataset) {
            (holder as GenericViewHolder).setConfig(dataset!![position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return pattern?.get(position) ?: super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        if (usingDataset && dataset != null) {
            return dataset.size
        }
        return customDatasetSize
    }

    abstract class GenericViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setConfig(data: ConfigData?)
        open fun shouldHaveListener(): Boolean = false
        open fun addListener(listener: EventListener?) {}
    }

    interface EventListener {
        fun onEvent()
    }
}
