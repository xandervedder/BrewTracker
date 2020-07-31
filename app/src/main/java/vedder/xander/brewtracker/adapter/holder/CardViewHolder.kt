package vedder.xander.brewtracker.adapter.holder

import android.view.View
import android.widget.TextView
import vedder.xander.brewtracker.R
import vedder.xander.brewtracker.adapter.GenericAdapter
import vedder.xander.brewtracker.model.Model
import vedder.xander.brewtracker.model.Recipe

class CardViewHolder(view: View) : GenericAdapter.GenericViewHolder(view) {
    private var title: TextView = view.findViewById(R.id.card_title)
    private var dateCreated: TextView = view.findViewById(R.id.card_date_created)
    private var brewType: TextView = view.findViewById(R.id.card_brew_type)

    override fun setConfig(data: Model) {
        if (data is Recipe) {
            title.text = data.name
            dateCreated.text = data.createdAt.toString()
            brewType.text = data.type
        }
    }

    class Factory : ViewHolderFactory {
        override fun assemble(view: View): GenericAdapter.GenericViewHolder {
            return CardViewHolder(view)
        }
    }
}
