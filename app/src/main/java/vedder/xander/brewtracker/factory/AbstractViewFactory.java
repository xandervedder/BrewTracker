package vedder.xander.brewtracker.factory;

import android.content.Context;

import vedder.xander.brewtracker.ui.view.AbstractView;

public interface AbstractViewFactory<T extends AbstractView> {
    T assemble(Context context);
}
