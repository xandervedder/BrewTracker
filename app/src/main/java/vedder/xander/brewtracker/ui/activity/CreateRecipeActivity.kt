package vedder.xander.brewtracker.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
import vedder.xander.brewtracker.pattern.SequentialViewTypePattern

import java.util.*

class CreateRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val dataset: MutableList<ConfigData> = ArrayList()
        dataset.add(TextEditConfig("Name", ""))
        dataset.add(TextEditConfig("Type", ""))

        dataset.add(ButtonConfig("Send signal to activity"))
        val viewHolders: MutableList<ViewHolderFactory> = ArrayList()
        viewHolders.add(TextInputViewHolder.Factory())
        viewHolders.add(ButtonViewHolder.Factory())

        val factories: MutableList<ViewFactory<out View?>> = ArrayList()
        factories.add(TextInputFactory())
        factories.add(ButtonFactory())

        val recyclerView = findViewById<RecyclerView>(R.id.ingredient_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GenericAdapter(
                dataset,
                factories,
                viewHolders,
                SequentialViewTypePattern("0:0:1", factories.size),
                object : GenericAdapter.EventListener {
                    override fun onEvent(data: List<ConfigData>) {
                        Log.d("teststuff", data.toString())
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
        intent.putExtra("name", name)
        intent.putExtra("type", type)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}