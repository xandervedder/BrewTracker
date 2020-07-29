package vedder.xander.brewtracker.model

import java.time.LocalDate

// TODO: maybe a way to go from ConfigData to Recipe and vice-versa?
data class Recipe(var createdAt: LocalDate, var name: String, var type: String)
