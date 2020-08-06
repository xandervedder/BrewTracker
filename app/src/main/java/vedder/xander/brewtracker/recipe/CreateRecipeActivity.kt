package vedder.xander.brewtracker.recipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.brew.model.brewTypeFromString
import vedder.xander.brewtracker.ingredient.IngredientsFragment
import vedder.xander.brewtracker.ingredient.model.Ingredient
import vedder.xander.brewtracker.recipe.model.Recipe
import vedder.xander.brewtracker.widget.BottomSheetDialog
import java.time.LocalDate

class CreateRecipeActivity : AppCompatActivity() {
    private val ingredientsFragment = IngredientsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        setUpTitle()
        setUpExposedMenu()
        setUpFragment()
        setUpListeners()
    }

    private fun setUpTitle() {
        findViewById<TextView>(R.id.toolbar_title).text = getString(R.string.title_activity_create_recipe)
    }

    private fun setUpExposedMenu() {
        val adapter = ArrayAdapter(
                applicationContext,
                R.layout.popup_dropdown_menu_item,
                // TODO: This should be done in a different way
                arrayOf("Beer", "Wine", "Mead")
        )
        val menu = findViewById<AutoCompleteTextView>(R.id.recipe_dropdown)
        menu.setAdapter(adapter)
    }

    private fun setUpFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.recipe_ingredient_container, ingredientsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    private fun setUpListeners() {
        findViewById<MaterialButton>(R.id.recipe_add_ingredient).setOnClickListener { addIngredient() }
        findViewById<MaterialButton>(R.id.recipe_add).setOnClickListener { addRecipe() }
    }

    private fun addIngredient() {
        val bottomSheetDialog = BottomSheetDialog()
        bottomSheetDialog.show(supportFragmentManager, null)
        bottomSheetDialog.listener = object : BottomSheetDialog.OnCancelListener {
            override fun onDismiss(ingredient: Ingredient) {
                ingredientsFragment.onIngredientCreated(ingredient)
            }
        }
    }

    private fun addRecipe() {
        val recipeName = findViewById<TextView>(R.id.recipe_name).text
        val recipeType = brewTypeFromString(findViewById<AutoCompleteTextView>(R.id.recipe_dropdown).text.toString())
        val recipeIngredients = ingredientsFragment.getIngredients()
        val intent = Intent()
        intent.putExtra("recipe", Recipe(LocalDate.now(), recipeName.toString(), recipeType, recipeIngredients))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
