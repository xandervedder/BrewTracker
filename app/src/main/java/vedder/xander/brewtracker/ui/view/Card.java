package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.model.Recipe;

/**
 * Might want to rename this to CardView
 */
public class Card extends LinearLayout {

    private Recipe recipe; // In the future this should be more generic

    public Card(Context context) {
        this(context, null);
    }

    public Card(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Card(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.card, this);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;

        // TODO: move this logic somewhere else
        TextView title = findViewById(R.id.card_title);
        title.setText(recipe.getName());
        TextView dateCreated = findViewById(R.id.card_date_created);
        dateCreated.setText(recipe.getCreatedAt().toString());
        TextView brewType = findViewById(R.id.card_brew_type);
        brewType.setText(recipe.getType());
    }
}
