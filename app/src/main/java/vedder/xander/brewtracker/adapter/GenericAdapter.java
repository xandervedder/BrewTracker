package vedder.xander.brewtracker.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedder.xander.brewtracker.factory.ViewFactory;
import vedder.xander.brewtracker.model.AbstractDataItem;
import vedder.xander.brewtracker.ui.view.AbstractView;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ViewFactory<? extends AbstractView>> viewFactories;
    private List<AbstractDataItem> dataset;

    private boolean usingDataset;
    private Integer customDatasetSize;
    private ViewMode viewMode;

    public GenericAdapter(List<AbstractDataItem> dataset,
                          @NonNull List<ViewFactory<? extends AbstractView>> factories,
                          @Nullable Integer customSize,
                          ViewMode viewMode) {
        this.dataset = dataset;
        this.viewFactories = factories;
        this.customDatasetSize = customSize;
        this.usingDataset = dataset != null;
        this.viewMode = viewMode;
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
        if (this.viewMode == ViewMode.HEADER)
            return position == 0 ? 0 : 1;

        if (this.viewMode == ViewMode.DEFAULT) // We don't want any other viewType
            return 0;

        if (this.viewMode == ViewMode.SEQUENTIAL)
            return position % this.viewFactories.size();

        if (this.viewMode == ViewMode.PAIRS) {
            if (position == 0 || position == 1) // Start, edge case
                return 0;

            if (position % 2 == 0) return (position / 2) % this.viewFactories.size();
            else return ((position - 1) / 2) % this.viewFactories.size();
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (this.customDatasetSize != null)
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

    public enum ViewMode {
        DEFAULT,
        SEQUENTIAL, // 1 2 or 1 2 3 or 1 2 3 4 ...
        PAIRS, // e.g. 11 22 or 11 22 33
        HEADER,
    }
}
