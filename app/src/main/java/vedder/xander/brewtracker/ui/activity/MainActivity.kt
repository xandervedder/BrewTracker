package vedder.xander.brewtracker.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ui.fragment.BrewFragment
import vedder.xander.brewtracker.ui.fragment.HomeFragment
import vedder.xander.brewtracker.ui.fragment.RecipeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.item_home -> loadFragment(HomeFragment())
                R.id.item_recipe -> loadFragment(RecipeFragment())
                R.id.item_brew -> loadFragment(BrewFragment())
                else -> false
            }
        }
        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        return true
    }
}