package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.model.AbstractDataItem;

// TODO: Might want to check out the best layout that we are extending
public abstract class AbstractView extends LinearLayout {

    public AbstractView(Context context) {
        this(context, null);
    }

    public AbstractView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AbstractView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public abstract void setDataItem(AbstractDataItem item);
}
