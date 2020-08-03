package vedder.xander.brewtracker.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import vedder.xander.brewtracker.R

class Toolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : Toolbar(context, attrs, defStyleAttr) {
    private fun setUpCreateActivityToolbar() {
        val rightIcon = findViewById<ImageView>(R.id.toolbar_account)
        rightIcon.visibility = View.GONE
        val leftIcon = findViewById<ImageView>(R.id.toolbar_drawer)
        leftIcon.setImageResource(R.drawable.ic_baseline_arrow_back_24)
        leftIcon.setOnClickListener { (context as Activity).finish() }
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        // TODO: Will have to be a custom attribute in the future:
        toolbarTitle.setText(R.string.title_activity_create_recipe)
    }

    init {
        View.inflate(context, R.layout.toolbar, this)
        val attrsArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.Toolbar,
                0, 0)
        try {
            val isCreateActivity = attrsArray.getBoolean(R.styleable.Toolbar_isCreateActivity, false)
            if (isCreateActivity) setUpCreateActivityToolbar()
        } finally {
            attrsArray.recycle()
        }
    }
}
