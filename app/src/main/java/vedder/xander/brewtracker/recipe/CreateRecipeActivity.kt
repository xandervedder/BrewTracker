package vedder.xander.brewtracker.recipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ingredient.model.Ingredient
import vedder.xander.brewtracker.recipe.model.Recipe
import vedder.xander.brewtracker.widget.BottomSheetDialog
import java.time.LocalDate

import java.util.*

class CreateRecipeActivity : AppCompatActivity() {
    private val ingredients: MutableList<Ingredient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        findViewById<TextView>(R.id.toolbar_title).text = getString(R.string.title_activity_create_recipe)
        setUpFloatingButton()
    }

    private fun createRecipe(name: String, type: String) {
        val intent = Intent()
        intent.putExtra("recipe", Recipe(LocalDate.now(), name, type, ingredients))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun setUpFloatingButton() {
        val button = findViewById<ExtendedFloatingActionButton>(R.id.fab_button)
        button.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, null)
            bottomSheetDialog.listener = object : BottomSheetDialog.OnCancelListener {
                override fun onDismiss(ingredient: Ingredient) {
                    ingredients.add(ingredient)
                }
            }
        }
    }
}
