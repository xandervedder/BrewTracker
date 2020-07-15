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

    // UI Related:
    private boolean singleViewtype;
    private boolean usingDataset;
    private Integer customDatasetSize;

    // A builder class would be usefull (because of the amount of options).
    public GenericAdapter(List<AbstractDataItem> dataset,
                          @NonNull List<ViewFactory<? extends AbstractView>> factories,
                          boolean singleViewtype,
                          @Nullable Integer customSize) {
        this.dataset = dataset;
        this.viewFactories = factories;
        this.singleViewtype = singleViewtype;
        this.usingDataset = dataset != null;
        this.customDatasetSize = customSize;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Choose from the list of viewFactories to assemble the right view (based on viewType)
        AbstractView view = this.viewFactories.get(viewType).assemble(parent.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Should only use `setDataItem` when the view actually needs data.

        // Otherwise this line will suffice:
        if (this.usingDataset)
            ((GenericViewHolder) holder).getItem().setDataItem(this.dataset.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (this.singleViewtype) // We don't want any other viewType
            return 0;

        // Here's where the magic will happen. Based on a passed variable or something else, we
        // should be able to get the right view based on that (I'm thinking of a list).
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
}
