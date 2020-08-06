package vedder.xander.brewtracker.ingredient.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ingredient.model.Ingredient

class IngredientAdapter(
        private val dataset: MutableList<Ingredient>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataset[position].let { (holder as IngredientViewHolder).setIngredient(it) }

    }

    fun addIngredient(ingredient: Ingredient) {
        dataset.add(ingredient)
    }

    inner class IngredientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ingredientName: TextView = view.findViewById(R.id.ingredient_name)
        private val ingredientAmount: TextView = view.findViewById(R.id.ingredient_amount)
        private val ingredientUnit: TextView = view.findViewById(R.id.ingredient_unit)

        fun setIngredient(ingredient: Ingredient) {
            ingredientName.text = ingredient.name
            ingredientAmount.text = ingredient.amount
            ingredientUnit.text = ingredient.unit.toString()
        }
    }
}
