package vedder.xander.brewtracker.ingredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ingredient.model.Ingredient
import vedder.xander.brewtracker.ingredient.model.UnitType
import vedder.xander.brewtracker.ingredient.ui.IngredientAdapter


class IngredientsFragment : Fragment(), IngredientListener {
    private val layoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private val ingredients: MutableList<Ingredient> = ArrayList()

    private lateinit var adapter: IngredientAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.ingredients_recyclerview)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = IngredientAdapter(ingredients)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onIngredientCreated(ingredient: Ingredient) {
        adapter.addIngredient(ingredient)
        // TODO: Just checking if it works, will replace with something faster
        adapter.notifyDataSetChanged()
    }

    override fun getIngredients(): MutableList<Ingredient> {
        return ingredients
    }
}
