package vedder.xander.brewtracker.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import org.jetbrains.annotations.NotNull
import vedder.xander.brewtracker.R
import kotlin.properties.Delegates

class BottomSheetDialog : BottomSheetDialogFragment() {
    private lateinit var listener: OnDismissListener
    private lateinit var viewToAttach: View
    private var closeableId = 0

    fun attach(@NotNull listener: OnDismissListener, @NotNull view: View, id: Int) {
        this.listener = listener
        this.viewToAttach = view
        this.closeableId = id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_bottom_sheet, container, false)
        view.findViewById<LinearLayout>(R.id.bottom_sheet_insertion).addView(viewToAttach)
        view.findViewById<MaterialButton>(closeableId).setOnClickListener {
            listener.onDismiss(view)
            dismiss()
        }
        return view
    }

    override fun getTheme(): Int = R.style.BrewTracker_BottomSheetDialogTheme

    interface OnDismissListener {
        fun onDismiss(view: View)
    }
}
