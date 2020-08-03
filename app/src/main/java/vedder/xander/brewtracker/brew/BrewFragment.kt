package vedder.xander.brewtracker.brew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vedder.xander.brewtracker.R

class BrewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_brew, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab: FloatingActionButton = activity!!.findViewById(R.id.fab_button)
        fab.visibility = View.GONE
    }
}
