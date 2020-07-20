package vedder.xander.brewtracker.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.ui.fragment.BrewFragment;
import vedder.xander.brewtracker.ui.fragment.HomeFragment;
import vedder.xander.brewtracker.ui.fragment.RecipeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home: return loadFragment(new HomeFragment());
                    case R.id.item_recipe: return loadFragment(new RecipeFragment());
                    case R.id.item_brew: return loadFragment(new BrewFragment());
                    default: return false;
                }
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }
}
