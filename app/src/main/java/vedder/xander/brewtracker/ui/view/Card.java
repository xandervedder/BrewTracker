package vedder.xander.brewtracker.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.model.AbstractDataItem;

public class Card extends AbstractView {

    private AbstractDataItem item;

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

    @Override
    public void setDataItem(AbstractDataItem item) {
        this.item = item;

        TextView title = findViewById(R.id.card_title);
        title.setText(item.get("name"));
        // This is better, but we need to provide a list with arguments that we want (or something like that).
        // Maybe a factory that fills this for us (something like that..)
        TextView dateCreated = findViewById(R.id.card_date_created);
        dateCreated.setText(item.get("createdAt"));
        TextView brewType = findViewById(R.id.card_brew_type);
        brewType.setText(item.get("type"));
    }

    public AbstractDataItem getItem() {
        return item;
    }
}
