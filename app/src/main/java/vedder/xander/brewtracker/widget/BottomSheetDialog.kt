package vedder.xander.brewtracker.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.ingredient.model.Ingredient

class BottomSheetDialog : BottomSheetDialogFragment() {
    var listener: OnCancelListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_bottom_sheet, container, false)
        view.findViewById<MaterialButton>(R.id.add_ingredient).setOnClickListener {
            listener?.onDismiss(Ingredient(
                    view.findViewById<TextInputLayout>(R.id.ingredient_name)?.editText?.text.toString(),
                    view.findViewById<TextInputLayout>(R.id.ingredient_amount)?.editText?.text.toString(),
                    view.findViewById<TextInputLayout>(R.id.ingredient_unit)?.editText?.text.toString()
            ))
            dismiss()
        }
        return view
    }

    override fun getTheme(): Int = R.style.BrewTracker_BottomSheetDialogTheme

    interface OnCancelListener {
        // TODO: Should be generalized (testing for now)
        fun onDismiss(ingredient: Ingredient)
    }
}
