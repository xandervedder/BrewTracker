package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.model.AbstractDataItem;

public class TextInput extends AbstractView {

    public TextInput(Context context) {
        this(context, null);
    }

    public TextInput(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TextInput(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.text_input, this);
    }

    @Override
    public void setDataItem(AbstractDataItem item) {
        // Do nothing.
    }
}
