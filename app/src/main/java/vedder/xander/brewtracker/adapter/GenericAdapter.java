package vedder.xander.brewtracker.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedder.xander.brewtracker.factory.ViewFactory;
import vedder.xander.brewtracker.model.AbstractDataItem;
import vedder.xander.brewtracker.pattern.ViewTypePattern;
import vedder.xander.brewtracker.ui.view.AbstractView;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ViewFactory<? extends AbstractView>> viewFactories;
    private List<AbstractDataItem> dataset;

    private boolean usingDataset;
    private Integer customDatasetSize;
    private ViewTypePattern pattern;

    public GenericAdapter(List<AbstractDataItem> dataset,
                          @NonNull List<ViewFactory<? extends AbstractView>> factories,
                          @Nullable Integer customSize,
                          ViewTypePattern pattern) {
        this.dataset = dataset;
        this.viewFactories = factories;
        this.customDatasetSize = customSize;
        this.usingDataset = dataset != null;
        this.pattern = pattern;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AbstractView view = this.viewFactories.get(viewType).assemble(parent.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (this.usingDataset)
            ((GenericViewHolder) holder).getItem().setDataItem(this.dataset.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        // This works fine now, but there is no option to specify when a pattern needs to repeat some other
        // view type, currently it's all sequential (which is fine most of the time, but not always)
        if (this.pattern != null)
            return pattern.get(position, this.dataset.size());

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (this.customDatasetSize != null) // Might remove this option...
            return this.customDatasetSize;

        return this.dataset.size();
    }

    public static class GenericViewHolder extends RecyclerView.ViewHolder {

        private AbstractView view;

        public GenericViewHolder(@NonNull AbstractView abstractView) {
            super(abstractView);
            this.view = abstractView;
        }

        public AbstractView getItem() {
            return view;
        }
    }
}
