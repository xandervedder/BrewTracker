package vedder.xander.brewtracker.recipe.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.recipe.model.Recipe

class RecipeAdapter(
        private var dataset: MutableList<Recipe>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataset[position].let { (holder as RecipeViewHolder).setRecipe(it) }
    }

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val brewType: TextView = view.findViewById(R.id.card_chip)
        private val dateCreated: TextView = view.findViewById(R.id.card_date_created)
        private val title: TextView = view.findViewById(R.id.card_title)

        fun setRecipe(recipe: Recipe) {
            brewType.text = recipe.type.toString()
            title.text = recipe.name
            dateCreated.text = recipe.createdAt.toString()
        }
    }
}
