package vedder.xander.brewtracker.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.ui.fragment.HomeFragment;
import vedder.xander.brewtracker.ui.fragment.RecipeFragment;

public class MainActivity extends AppCompatActivity {

//    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home: return loadFragment(new HomeFragment());
                    case R.id.page_recipe: return loadFragment(new RecipeFragment());
                    case R.id.page_brew:
                    default: return false;
                }
            }
        });
        this.loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Might need to be moved to a fragment instead, or maybe not? :)
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK && data != null) {
//                Bundle bundle = data.getExtras();
//                this.recipes.add(new Recipe(
//                        LocalDate.now(),
//                        bundle.get("name").toString(),
//                        bundle.get("type").toString()
//                ));
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        updateRecyclerView();
//                    }
//                }, 500);
//            }
//        }
//    }
}
