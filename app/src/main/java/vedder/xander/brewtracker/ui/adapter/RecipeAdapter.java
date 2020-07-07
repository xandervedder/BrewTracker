package vedder.xander.brewtracker.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedder.xander.brewtracker.model.Recipe;
import vedder.xander.brewtracker.ui.view.Card;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> dataset;

    public RecipeAdapter(List<Recipe> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Card card = new Card(parent.getContext());
        card.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new RecipeViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecipeViewHolder) holder).getCard().setRecipe(this.dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private View card;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.card = itemView;
        }

        public Card getCard() {
            return (Card) card;
        }
    }
}
