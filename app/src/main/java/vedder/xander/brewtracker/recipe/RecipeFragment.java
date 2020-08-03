package vedder.xander.brewtracker.recipe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.recipe.model.Recipe;
import vedder.xander.brewtracker.recipe.ui.RecipeAdapter;

import static android.app.Activity.RESULT_OK;

public class RecipeFragment extends Fragment {
    private static final int REQUEST_CODE = 1;

    private List<Recipe> recipes;
    private RecyclerView recyclerView;

    public RecipeFragment() {
        this.recipes = new ArrayList<>();

        this.recipes.add(new Recipe(LocalDate.now(),"Test 1", "Beer", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 2", "Cider", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 3", "Cider", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 4", "Mead", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 5", "Beer", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 6", "Cider", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 7", "Mead", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 8", "Cider", new ArrayList<>()));
        this.recipes.add(new Recipe(LocalDate.now(),"Test 9", "Beer", new ArrayList<>()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final FloatingActionButton fab = getActivity().findViewById(R.id.fab_button);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getBaseContext(), CreateRecipeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        recyclerView = getView().findViewById(R.id.recipes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecipeAdapter(recipes));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) fab.hide();
                else if (dy < 0) fab.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getExtras();
                Recipe recipe = bundle.getParcelable("recipe");
                this.recipes.add(recipe);
                new Handler().postDelayed(this::updateRecyclerView, 500);
            }
        }
    }

    private void updateRecyclerView() {
        this.recyclerView.smoothScrollToPosition(this.recipes.size() - 1);
    }
}
