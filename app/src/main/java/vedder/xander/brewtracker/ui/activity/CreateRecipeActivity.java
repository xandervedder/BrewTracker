package vedder.xander.brewtracker.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import vedder.xander.brewtracker.R;

public class CreateRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        Button button = findViewById(R.id.create_recipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRecipe();
            }
        });
    }

    private void createRecipe() {
        TextInputLayout layoutName = findViewById(R.id.recipe_name);
        TextInputLayout layoutType = findViewById(R.id.recipe_type);
        Intent intent = new Intent();
        intent.putExtra("name", layoutName.getEditText().getText());
        intent.putExtra("type", layoutType.getEditText().getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
