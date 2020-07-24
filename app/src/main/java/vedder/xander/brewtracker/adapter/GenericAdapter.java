package vedder.xander.brewtracker.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedder.xander.brewtracker.adapter.holder.ViewHolderFactory;
import vedder.xander.brewtracker.config.ConfigData;
import vedder.xander.brewtracker.factory.ViewFactory;
import vedder.xander.brewtracker.pattern.ViewTypePattern;
import vedder.xander.brewtracker.ui.view.AbstractView;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ConfigData> dataset;
    private List<ViewFactory<? extends View>> viewFactories;
    private List<ViewHolderFactory> viewHolderFactories;

    private boolean usingDataset;
    private Integer customDatasetSize;
    private ViewTypePattern pattern;

    public GenericAdapter(List<ConfigData> dataset,
                          @NonNull List<ViewFactory<? extends View>> factories,
                          @NonNull List<ViewHolderFactory> holderFactories,
                          @Nullable Integer customSize,
                          ViewTypePattern pattern) {
        this.dataset = dataset;
        this.viewFactories = factories;
        this.viewHolderFactories = holderFactories;
        this.customDatasetSize = customSize;
        this.usingDataset = dataset != null;
        this.pattern = pattern;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.viewFactories.get(viewType).assemble(parent.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return this.viewHolderFactories.get(viewType).assemble(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (this.usingDataset)
            ((GenericViewHolder) holder).setConfig(this.dataset.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (this.pattern != null)
            return pattern.get(position);

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (this.customDatasetSize != null) // Might remove this option...
            return this.customDatasetSize;

        return this.dataset.size();
    }

    public abstract static class GenericViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public GenericViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
        }

        // Subclass this viewholder, and implement your own version:
        public abstract void setConfig(ConfigData data);
    }
}
