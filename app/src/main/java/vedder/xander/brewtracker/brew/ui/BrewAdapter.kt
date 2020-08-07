package vedder.xander.brewtracker.brew.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.brew.model.Brew

class BrewAdapter(private val dataset: MutableList<Brew>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return BrewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BrewViewHolder).setBrew(dataset[position])
    }

    fun addBrew(brew: Brew) {
        dataset.add(brew)
    }

    inner class BrewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.card_title)
        private val dateCreated: TextView = view.findViewById(R.id.card_date_created)
        private val chip: TextView = view.findViewById(R.id.card_chip)

        fun setBrew(brew: Brew) {
            title.text = brew.recipe.name
            dateCreated.text = brew.dateCreated.toString()
            chip.text = brew.status.toString()
        }
    }
}
