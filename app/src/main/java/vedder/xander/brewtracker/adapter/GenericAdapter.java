package vedder.xander.brewtracker.adapter;

import android.util.Log;
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

    private boolean singleViewtype;
    private boolean usingDataset;
    private Integer customDatasetSize;
    private ViewMode viewMode;

    public GenericAdapter(List<AbstractDataItem> dataset,
                          @NonNull List<ViewFactory<? extends AbstractView>> factories,
                          @Nullable Integer customSize) {
        this.dataset = dataset;
        this.viewFactories = factories;
        this.customDatasetSize = customSize;
        this.singleViewtype = factories.size() == 1;
        this.usingDataset = dataset != null;
        this.viewMode = ViewMode.SEQUENTIAL;
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

    private int x = 0;

    @Override
    public int getItemViewType(int position) {
        if (this.singleViewtype) // We don't want any other viewType
            return 0;

        if (this.viewMode == ViewMode.SEQUENTIAL)
            return position % this.viewFactories.size();

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
        SEQUENTIAL, // 1 2 or 1 2 3 or 1 2 3 4 ...
    }
}
