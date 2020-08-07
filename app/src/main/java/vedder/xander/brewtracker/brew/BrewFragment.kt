package vedder.xander.brewtracker.brew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.brew.model.Brew
import vedder.xander.brewtracker.brew.model.BrewStatus
import vedder.xander.brewtracker.brew.model.BrewType
import vedder.xander.brewtracker.brew.ui.BrewAdapter
import vedder.xander.brewtracker.recipe.model.Recipe
import vedder.xander.brewtracker.widget.BottomSheetDialog
import java.time.LocalDate

class BrewFragment : Fragment() {
    private val brews: MutableList<Brew> = ArrayList()

    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrewAdapter

    init {
        val recipe1 = Recipe(LocalDate.now(), "test 1", BrewType.BEER, ArrayList())
        val recipe2 = Recipe(LocalDate.now(), "test 2", BrewType.BEER, ArrayList())
        val recipe3 = Recipe(LocalDate.now(), "test 3", BrewType.BEER, ArrayList())
        val recipe4 = Recipe(LocalDate.now(), "test 4", BrewType.BEER, ArrayList())
        brews.add(Brew(LocalDate.now().plusDays(2), recipe1, BrewStatus.DOING))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe1, BrewStatus.FINISHED))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe2, BrewStatus.DOING))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe2, BrewStatus.DOING))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe3, BrewStatus.FINISHED))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe3, BrewStatus.DOING))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe4, BrewStatus.DOING))
        brews.add(Brew(LocalDate.now().plusDays(2), recipe4, BrewStatus.FINISHED))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_brew, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpFab()
        setUpRecyclerView()
    }

    private fun setUpFab() {
        fab = activity!!.findViewById(R.id.recipe_fab_button)
        fab.setOnClickListener { showModal() }
    }

    private fun showModal() {
        val view = View.inflate(context, R.layout.content_brew_bottomsheet, null)
        setUpMenu(view.findViewById(R.id.brew_dropdown))
        val bottomSheetDialog = BottomSheetDialog()
        bottomSheetDialog.attach(object : BottomSheetDialog.OnDismissListener {
            override fun onDismiss(view: View) {
                createBrewFromView(view)
            }
        }, view, R.id.add_brew)
        activity?.supportFragmentManager?.let { bottomSheetDialog.show(it, null) }
    }

    private fun createBrewFromView(view: View) {
        adapter.addBrew(Brew(
                LocalDate.now(),
                // TODO: Temporary:
                Recipe(
                        LocalDate.now(),
                        view.findViewById<AutoCompleteTextView>(R.id.brew_dropdown).text.toString(),
                        BrewType.BEER,
                        ArrayList()
                ),
                BrewStatus.DOING
        ))
        adapter.notifyDataSetChanged()
    }

    private fun setUpMenu(textView: AutoCompleteTextView) {
        // TODO: potentially change this to a recyclerview in the dialog
        val adapter = context?.let {
            ArrayAdapter(
                    it,
                    R.layout.popup_dropdown_menu_item,
                    arrayOf("Recipe1", "Recipe2", "Recipe3")
            )
        }
        textView.setAdapter(adapter)
    }

    private fun setUpRecyclerView() {
        recyclerView = view!!.findViewById(R.id.brew_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BrewAdapter(brews)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) fab.hide() else if (dy < 0) fab.show()
            }
        })
    }
}
