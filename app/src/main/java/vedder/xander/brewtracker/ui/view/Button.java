package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import vedder.xander.brewtracker.R;

public class Button extends LinearLayout {
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
}
