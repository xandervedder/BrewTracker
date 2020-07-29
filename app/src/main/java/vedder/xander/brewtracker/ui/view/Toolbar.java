package vedder.xander.brewtracker.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import vedder.xander.brewtracker.R;

public class Toolbar extends androidx.appcompat.widget.Toolbar {
    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.toolbar, this);

        TypedArray attrsArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Toolbar,
                0, 0);

        try {
            boolean isCreateActivity = attrsArray.getBoolean(R.styleable.Toolbar_isCreateActivity, false);
            if (isCreateActivity) this.setUpCreateActivityToolbar();
        } finally {
            attrsArray.recycle();
        }
    }

    private void setUpCreateActivityToolbar() {
        ImageView rightIcon = findViewById(R.id.toolbar_account);
        rightIcon.setVisibility(View.GONE);
        ImageView leftIcon = findViewById(R.id.toolbar_drawer);
        leftIcon.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        leftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        // TODO: Will have to be a custom attribute in the future:
        toolbarTitle.setText(R.string.title_activity_create_recipe);
    }
}
