package vedder.xander.brewtracker.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ui.fragment.BrewFragment
import vedder.xander.brewtracker.ui.fragment.HomeFragment
import vedder.xander.brewtracker.ui.fragment.RecipeFragment

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val recipeFragment = RecipeFragment()
    private val brewFragment = BrewFragment()

    // HomeFragment should be the first one to load
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.item_home -> replaceFragment(homeFragment)
                R.id.item_recipe -> replaceFragment(recipeFragment)
                R.id.item_brew -> replaceFragment(brewFragment)
                else -> false
            }
        }
        replaceFragment(activeFragment)
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
}