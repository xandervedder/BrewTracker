package vedder.xander.brewtracker.config

import java.time.LocalDate

data class CardConfig(val title: String, val dateCreated: LocalDate, val brewType: String) : ConfigData
