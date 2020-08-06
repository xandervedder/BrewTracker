package vedder.xander.brewtracker.recipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.brew.model.BrewType
import vedder.xander.brewtracker.recipe.model.Recipe
import vedder.xander.brewtracker.recipe.ui.RecipeAdapter
import java.time.LocalDate
import java.util.*

class RecipeFragment : Fragment() {
    private val recipes: MutableList<Recipe> = ArrayList()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab: FloatingActionButton = activity!!.findViewById(R.id.recipe_fab_button)
        fab.visibility = View.VISIBLE
        fab.setOnClickListener {
            val intent = Intent(activity!!.baseContext, CreateRecipeActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        recyclerView = getView()!!.findViewById(R.id.recipes_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecipeAdapter(recipes)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) fab.hide() else if (dy < 0) fab.show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE && data != null) {
                val bundle = data.extras
                val recipe: Recipe? = bundle!!.getParcelable("recipe")
                if (recipe != null) {
                    recipes.add(recipe)
                }
                Handler().postDelayed({ updateRecyclerView() }, 500)
            }
        }
    }

    private fun updateRecyclerView() {
        recyclerView.smoothScrollToPosition(recipes.size - 1)
    }

    companion object {
        const val REQUEST_CODE = 1
    }

    init {
        recipes.add(Recipe(LocalDate.now(), "Test 1", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 2", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 3", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 4", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 5", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 6", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 7", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 8", BrewType.BEER, ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 9", BrewType.BEER, ArrayList()))
    }
}
