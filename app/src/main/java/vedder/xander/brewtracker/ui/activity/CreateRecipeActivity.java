package vedder.xander.brewtracker.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.adapter.GenericAdapter;
import vedder.xander.brewtracker.factory.ButtonFactory;
import vedder.xander.brewtracker.factory.CardFactory;
import vedder.xander.brewtracker.factory.TextInputFactory;
import vedder.xander.brewtracker.factory.ViewFactory;
import vedder.xander.brewtracker.ui.view.AbstractView;

public class CreateRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        List<ViewFactory<? extends AbstractView>> factories = new ArrayList<>();
        factories.add(new CardFactory());
        factories.add(new TextInputFactory());
        factories.add(new ButtonFactory());

        RecyclerView recyclerView = findViewById(R.id.recipe_create_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GenericAdapter(null, factories, 50));

//        Button button = findViewById(R.id.create_recipe);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createRecipe();
//            }
//        });
    }

    private void createRecipe() {
//        TextInputLayout layoutName = findViewById(R.id.recipe_name);
//        TextInputLayout layoutType = findViewById(R.id.recipe_type);
//        Intent intent = new Intent();
//        intent.putExtra("name", layoutName.getEditText().getText());
//        intent.putExtra("type", layoutType.getEditText().getText());
//        setResult(RESULT_OK, intent);
//        finish();
    }
}
