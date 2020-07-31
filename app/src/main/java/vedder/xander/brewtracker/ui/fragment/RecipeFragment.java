package vedder.xander.brewtracker.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.adapter.holder.CardViewHolder;
import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory;
import vedder.xander.brewtracker.config.CardConfig;
import vedder.xander.brewtracker.config.ConfigData;
import vedder.xander.brewtracker.factory.ViewFactory;
import vedder.xander.brewtracker.factory.CardFactory;
import vedder.xander.brewtracker.adapter.GenericAdapter;
import vedder.xander.brewtracker.model.Recipe;
import vedder.xander.brewtracker.pattern.SequentialViewTypePattern;
import vedder.xander.brewtracker.ui.activity.CreateRecipeActivity;

import static android.app.Activity.RESULT_OK;

public class RecipeFragment extends Fragment {

    private static final int REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private List<ConfigData> recipes;

    public RecipeFragment() {
        this.recipes = new ArrayList<>();

        this.recipes.add(new CardConfig("Test 1", LocalDate.now(),  "Beer"));
        this.recipes.add(new CardConfig("Test 2", LocalDate.now(),  "Cider"));
        this.recipes.add(new CardConfig("Test 3", LocalDate.now(),  "Cider"));
        this.recipes.add(new CardConfig("Test 4", LocalDate.now(),  "Mead"));
        this.recipes.add(new CardConfig("Test 5", LocalDate.now(),  "Beer"));
        this.recipes.add(new CardConfig("Test 6", LocalDate.now(),  "Cider"));
        this.recipes.add(new CardConfig("Test 7", LocalDate.now(),  "Mead"));
        this.recipes.add(new CardConfig("Test 8", LocalDate.now(),  "Cider"));
        this.recipes.add(new CardConfig("Test 9", LocalDate.now(),  "Beer"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("DuplicateRecyclerViewBug", String.valueOf(this.recipes.size()));

        final FloatingActionButton fab = getActivity().findViewById(R.id.fab_button);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getBaseContext(), CreateRecipeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        List<ViewFactory<? extends LinearLayout>> factories = new ArrayList<>();
        factories.add(new CardFactory());

        List<ViewHolderFactory> viewHolderFactories = new ArrayList<>();
        viewHolderFactories.add(new CardViewHolder.Factory());

        this.recyclerView = getView().findViewById(R.id.recipes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new GenericAdapter(
                this.recipes,
                factories,
                viewHolderFactories,
                new SequentialViewTypePattern("0", factories.size()),
                data -> {} // temp
        ));
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
                this.recipes.add(new CardConfig(recipe.getName(), recipe.getCreatedAt(), recipe.getType()));

                new Handler().postDelayed(this::updateRecyclerView, 500);
            }
        }
    }

    private void updateRecyclerView() {
        this.recyclerView.smoothScrollToPosition(this.recipes.size() - 1);
    }
}
