package vedder.xander.brewtracker.recipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.recipe.model.Recipe
import vedder.xander.brewtracker.recipe.ui.RecipeAdapter
import java.time.LocalDate
import java.util.*

class RecipeFragment : Fragment() {
    private val recipes: MutableList<Recipe?>
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab: FloatingActionButton = activity!!.findViewById(R.id.fab_button)
        fab.visibility = View.VISIBLE
        fab.setOnClickListener { v: View? ->
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
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val bundle = data.extras
                val recipe: Recipe? = bundle!!.getParcelable("recipe")
                recipes.add(recipe)
                Handler().postDelayed({ updateRecyclerView() }, 500)
            }
        }
    }

    private fun updateRecyclerView() {
        recyclerView!!.smoothScrollToPosition(recipes.size - 1)
    }

    companion object {
        private const val REQUEST_CODE = 1
    }

    init {
        recipes = ArrayList()
        recipes.add(Recipe(LocalDate.now(), "Test 1", "Beer", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 2", "Cider", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 3", "Cider", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 4", "Mead", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 5", "Beer", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 6", "Cider", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 7", "Mead", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 8", "Cider", ArrayList()))
        recipes.add(Recipe(LocalDate.now(), "Test 9", "Beer", ArrayList()))
    }
}
