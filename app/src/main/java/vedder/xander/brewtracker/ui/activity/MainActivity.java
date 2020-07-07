package vedder.xander.brewtracker.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.time.LocalDate;
import java.util.Arrays;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.model.Recipe;
import vedder.xander.brewtracker.ui.adapter.RecipeAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Recipe recipe1 = new Recipe(LocalDate.now(), "Test 1", "Cider");
        Recipe recipe2 = new Recipe(LocalDate.now(), "Test 2", "Beer");
        Recipe recipe3 = new Recipe(LocalDate.now(), "Test 3", "Mead");
        Recipe recipe4 = new Recipe(LocalDate.now(), "Test 4", "Mead");
        Recipe recipe5 = new Recipe(LocalDate.now(), "Test 5", "Cider");
        Recipe recipe6 = new Recipe(LocalDate.now(), "Test 6", "Beer");
        Recipe recipe7 = new Recipe(LocalDate.now(), "Test 7", "Beer");

        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recipes_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipeAdapter(Arrays.asList(recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7)));
    }
}
