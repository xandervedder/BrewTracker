package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.model.AbstractDataItem;

public class Button extends AbstractView {

    public Button(Context context) {
        this(context, null);
    }

    public Button(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.button, this);
    }

    @Override
    public void setDataItem(AbstractDataItem item) {
        // nothing yet
    }
}
