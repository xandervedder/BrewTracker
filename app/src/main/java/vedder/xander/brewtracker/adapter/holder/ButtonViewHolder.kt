package vedder.xander.brewtracker.adapter.holder

import android.view.View
import com.google.android.material.button.MaterialButton
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.adapter.GenericAdapter
import vedder.xander.brewtracker.config.ButtonConfig
import vedder.xander.brewtracker.config.ConfigData

class ButtonViewHolder(view: View) : GenericAdapter.GenericViewHolder(view) {
    private var button: MaterialButton = view.findViewById(R.id.button)

    override fun setConfig(data: ConfigData) {
        if (data is ButtonConfig) {
            button.text = data.text
        }
    }

    override fun shouldHaveListener(): Boolean = true

    override fun addListener(listener: GenericAdapter.EventListener) {
        button.setOnClickListener { listener.onEvent() }
    }

    class Factory : ViewHolderFactory {
        override fun assemble(view: View): GenericAdapter.GenericViewHolder {
            return ButtonViewHolder(view)
        }
    }
}