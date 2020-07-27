package vedder.xander.brewtracker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.adapter.GenericAdapter;
import vedder.xander.brewtracker.adapter.holder.ButtonViewHolder;
import vedder.xander.brewtracker.adapter.holder.TextInputViewHolder;
import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory;
import vedder.xander.brewtracker.config.ButtonConfig;
import vedder.xander.brewtracker.config.ConfigData;
import vedder.xander.brewtracker.config.TextEditConfig;
import vedder.xander.brewtracker.factory.ButtonFactory;
import vedder.xander.brewtracker.pattern.SequentialViewTypePattern;
import vedder.xander.brewtracker.factory.TextInputFactory;
import vedder.xander.brewtracker.factory.ViewFactory;

public class CreateRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        List<ConfigData> dataset = new ArrayList<>();
        dataset.add(new TextEditConfig("Name", ""));
        dataset.add(new TextEditConfig("Type", ""));
        dataset.add(new ButtonConfig("Send signal to activity"));

        List<ViewHolderFactory> viewHolders = new ArrayList<>();
        viewHolders.add(new TextInputViewHolder.Factory());
        viewHolders.add(new ButtonViewHolder.Factory());

        List<ViewFactory<? extends View>> factories = new ArrayList<>();
        factories.add(new TextInputFactory());
        factories.add(new ButtonFactory());

        RecyclerView recyclerView = findViewById(R.id.ingredient_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GenericAdapter(
                dataset,
                factories,
                viewHolders,
                new SequentialViewTypePattern("0:0:1", factories.size()),
                data -> {
                    Log.d("teststuff", data.toString());
                    createRecipe(
                            ((TextEditConfig) data.get(0)).getText(),
                            ((TextEditConfig) data.get(1)).getText()
                    );
                }
        ));
    }

    private void createRecipe(String name, String type) {
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("type", type);
        setResult(RESULT_OK, intent);
        finish();
    }
}
