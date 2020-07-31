package vedder.xander.brewtracker.adapter.holder

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.adapter.GenericAdapter
import vedder.xander.brewtracker.model.Model
import vedder.xander.brewtracker.model.Text

class TextInputViewHolder(view: View) : GenericAdapter.GenericViewHolder(view) {
    private var textInput: TextInputLayout = view.findViewById(R.id.text_input)

    override fun setConfig(data: Model) {
        if (data is Text) {
            textInput.hint = data.hint
            textInput.editText?.addTextChangedListener {
                data.text = textInput.editText?.text.toString()
            }
        }
    }

    class Factory : ViewHolderFactory {
        override fun assemble(view: View): GenericAdapter.GenericViewHolder {
            return TextInputViewHolder(view)
        }
    }
}
