package vedder.xander.brewtracker.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory
import vedder.xander.brewtracker.factory.ViewFactory
import vedder.xander.brewtracker.model.Model
import vedder.xander.brewtracker.pattern.ViewTypePattern

class GenericAdapter(
        private val dataset: List<Model>,
        private val viewFactories: List<ViewFactory<out View>>,
        private val viewHolderFactories: List<ViewHolderFactory>,
        private val pattern: ViewTypePattern?,
        private val listener: EventListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = viewFactories[viewType].assemble(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val holder = viewHolderFactories[viewType].assemble(view)
        if (holder.shouldHaveListener()) {
            holder.addListener(listener) { getModelsByPositions(listOf(0, 1)) }
        }
        return viewHolderFactories[viewType].assemble(view)
    }

    private fun getModelsByPositions(positions: List<Int>): MutableList<Model> {
        val models: MutableList<Model> = ArrayList()
        for (position in positions) {
            getModelByPosition(position)?.let { models.add(it) }
        }
        return models
    }

    private fun getModelByPosition(position: Int): Model? {
        return dataset[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenericViewHolder).setConfig(dataset[position])
    }

    override fun getItemViewType(position: Int): Int {
        return pattern?.get(position) ?: super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    abstract class GenericViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setConfig(data: Model)
        open fun shouldHaveListener(): Boolean = false
        open fun addListener(listener: EventListener?, function: () -> List<Model>) {}
    }

    interface EventListener {
        fun onEvent(data: List<Model>)
    }
}
