package vedder.xander.brewtracker.ingredient

import vedder.xander.brewtracker.ingredient.model.Ingredient

interface IngredientListener {
    fun onIngredientCreated(ingredient: Ingredient)
    fun getIngredients(): MutableList<Ingredient>
}
