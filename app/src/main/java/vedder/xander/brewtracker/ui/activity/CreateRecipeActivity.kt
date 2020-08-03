package vedder.xander.brewtracker.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.model.Ingredient
import vedder.xander.brewtracker.model.Recipe
import vedder.xander.brewtracker.ui.modal.BottomSheetDialog
import java.time.LocalDate

import java.util.*

class CreateRecipeActivity : AppCompatActivity() {
    private val ingredients: MutableList<Ingredient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
        setUpFloatingButton()
    }

    private fun createRecipe(name: String, type: String) {
        val intent = Intent()
        intent.putExtra("recipe", Recipe(LocalDate.now(), name, type,  ingredients))
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
