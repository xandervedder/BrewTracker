package vedder.xander.brewtracker.factory;

import android.content.Context;
import android.view.View;

public interface ViewFactory<T extends View> {

    T assemble(Context context);
}
