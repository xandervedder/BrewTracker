package vedder.xander.brewtracker.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.adapter.GenericAdapter
import vedder.xander.brewtracker.config.ConfigData
import vedder.xander.brewtracker.config.TextEditConfig

class TextInputViewHolder(view: View) : GenericAdapter.GenericViewHolder(view) {
    private var textInput: TextInputLayout = view.findViewById(R.id.text_input)

    override fun setConfig(data: ConfigData) {
        if (data is TextEditConfig) {
            textInput.hint = data.hint
        }
    }

    class Factory : ViewHolderFactory {
        override fun assemble(view: View): RecyclerView.ViewHolder {
            return TextInputViewHolder(view)
        }
    }
}