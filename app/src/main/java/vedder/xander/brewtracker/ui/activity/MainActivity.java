package vedder.xander.brewtracker.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.factory.CardFactory;
import vedder.xander.brewtracker.model.AbstractDataItem;
import vedder.xander.brewtracker.model.Recipe;
import vedder.xander.brewtracker.ui.adapter.GenericAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private List<AbstractDataItem> recipes;
    private RecyclerView recyclerView;

    public MainActivity() {
        this.recipes = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test data (will be removed soon)
        this.recipes.add(new Recipe(LocalDate.now(), "Test 1", "Cider"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 2", "Beer"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 3", "Mead"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 4", "Mead"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 5", "Cider"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 6", "Beer"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 7", "Beer"));

        final FloatingActionButton fab = findViewById(R.id.add_recipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateRecipeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        this.recyclerView = findViewById(R.id.recipes_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GenericAdapter(this.recipes, new CardFactory()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) fab.hide();
                    else if (dy < 0) fab.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                this.recipes.add(new Recipe(
                        LocalDate.now(),
                        bundle.get("name").toString(),
                        bundle.get("type").toString()
                ));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateRecyclerView();
                    }
                }, 500);
            }
        }
    }

    private void updateRecyclerView() {
        this.recyclerView.smoothScrollToPosition(this.recipes.size() - 1);
    }
}
