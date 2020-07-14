package vedder.xander.brewtracker.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.factory.CardFactory;
import vedder.xander.brewtracker.model.AbstractDataItem;
import vedder.xander.brewtracker.model.Recipe;
import vedder.xander.brewtracker.adapter.GenericAdapter;

public class RecipeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<AbstractDataItem> recipes;

    public RecipeFragment() {
        this.recipes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.recipes.add(new Recipe(LocalDate.now(), "Test 1", "Cider"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 2", "Beer"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 3", "Mead"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 4", "Mead"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 5", "Cider"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 6", "Beer"));
        this.recipes.add(new Recipe(LocalDate.now(), "Test 7", "Beer"));

        //        final FloatingActionButton fab = findViewById(R.id.add_recipe);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), CreateRecipeActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        });

        this.recyclerView = getView().findViewById(R.id.recipes_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new GenericAdapter(this.recipes, new CardFactory()));
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0) fab.hide();
//                else if (dy < 0) fab.show();
//            }
//        });
    }

    private void updateRecyclerView() {
        this.recyclerView.smoothScrollToPosition(this.recipes.size() - 1);
    }
}
