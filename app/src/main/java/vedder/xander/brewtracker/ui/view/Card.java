package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.R;

public class Card extends LinearLayout {
    public Card(Context context) {
        this(context, null);
    }

    public Card(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Card(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.card, this);
    }
}
