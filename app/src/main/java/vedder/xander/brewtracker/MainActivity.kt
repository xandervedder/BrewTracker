package vedder.xander.brewtracker

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import vedder.xander.brewtracker.brew.BrewFragment
import vedder.xander.brewtracker.home.HomeFragment
import vedder.xander.brewtracker.recipe.RecipeFragment


class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val recipeFragment = RecipeFragment()
    private val brewFragment = BrewFragment()
    private lateinit var titleTextView: TextView

    // HomeFragment should be the first one to load
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.toolbar_title)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.item_home -> {
                    replaceTitleText(R.string.fragment_home_title)
                    return@setOnNavigationItemSelectedListener replaceFragment(homeFragment)
                }
                R.id.item_recipe -> {
                    replaceTitleText(R.string.fragment_recipe_title)
                    return@setOnNavigationItemSelectedListener replaceFragment(recipeFragment)
                }
                R.id.item_brew -> {
                    replaceTitleText(R.string.fragment_brew_title)
                    return@setOnNavigationItemSelectedListener replaceFragment(brewFragment)
                }
                else -> false
            }
        }
        replaceFragment(activeFragment)
        replaceTitleText(R.string.fragment_home_title)
    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager
                .beginTransaction()
                .hide(activeFragment)
                .show(fragment)
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        activeFragment = fragment
        return true
    }

    private fun replaceTitleText(newTitleId: Int) {
        // TODO: animation?
        titleTextView.text = getString(newTitleId);
    }
}
