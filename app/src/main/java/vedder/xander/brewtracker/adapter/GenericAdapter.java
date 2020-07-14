package vedder.xander.brewtracker.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedder.xander.brewtracker.factory.AbstractViewFactory;
import vedder.xander.brewtracker.model.AbstractDataItem;
import vedder.xander.brewtracker.ui.view.AbstractView;

/**
 * This could potentially be the only adapter we need.
 */
public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AbstractViewFactory<? extends AbstractView> viewFactory;
    private List<AbstractDataItem> dataset;

    public GenericAdapter(List<AbstractDataItem> dataset, AbstractViewFactory<? extends AbstractView> factory) {
        // TODO: eventually the dataset type will have to be wrapped by another type,
        // TODO: so we can add headers and footers to the recyclerview.
        this.dataset = dataset;
        this.viewFactory = factory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AbstractView view = this.viewFactory.assemble(parent.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new GenericViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((GenericViewHolder) holder).getItem().setDataItem(this.dataset.get(position));
    }

    @Override
    public int getItemCount() {
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
