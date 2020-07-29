package vedder.xander.brewtracker.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.adapter.GenericAdapter
import vedder.xander.brewtracker.adapter.holder.ButtonViewHolder
import vedder.xander.brewtracker.adapter.holder.TextInputViewHolder
import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory
import vedder.xander.brewtracker.config.ButtonConfig
import vedder.xander.brewtracker.config.ConfigData
import vedder.xander.brewtracker.config.TextEditConfig
import vedder.xander.brewtracker.factory.ButtonFactory
import vedder.xander.brewtracker.factory.TextInputFactory
import vedder.xander.brewtracker.factory.ViewFactory
import vedder.xander.brewtracker.model.Ingredient
import vedder.xander.brewtracker.model.Recipe
import vedder.xander.brewtracker.pattern.SequentialViewTypePattern
import vedder.xander.brewtracker.ui.modal.BottomSheetDialog
import java.time.LocalDate

import java.util.*

class CreateRecipeActivity : AppCompatActivity() {
    private val ingredients: MutableList<Ingredient> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val dataset: MutableList<ConfigData> = ArrayList()
        dataset.add(TextEditConfig("Name", ""))
        dataset.add(TextEditConfig("Type", ""))
        dataset.add(ButtonConfig("Create Recipe")) // String should be a constant or ID (attrs.xml)

        val viewHolders: MutableList<ViewHolderFactory> = ArrayList()
        viewHolders.add(TextInputViewHolder.Factory())
        viewHolders.add(ButtonViewHolder.Factory())

        val factories: MutableList<ViewFactory<out View>> = ArrayList()
        factories.add(TextInputFactory())
        factories.add(ButtonFactory())

        setUpRecyclerView(dataset, viewHolders, factories)
        setUpFloatingButton()
    }

    private fun setUpRecyclerView(dataset: MutableList<ConfigData>,
                                  viewHolders: MutableList<ViewHolderFactory>,
                                  factories: MutableList<ViewFactory<out View>>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ingredient_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GenericAdapter(
                dataset,
                factories,
                viewHolders,
                SequentialViewTypePattern("0:0:1", factories.size),
                object : GenericAdapter.EventListener {
                    override fun onEvent(data: List<ConfigData>) {
                        createRecipe(
                                (data[0] as TextEditConfig).text,
                                (data[1] as TextEditConfig).text
                        )
                    }
                }
        )
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
