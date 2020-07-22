package vedder.xander.brewtracker.pattern

const val STRING_DELIMITER = ":"

interface ViewTypePattern {

    fun compile(): ViewTypePattern

    fun get(position: Int): Int
}
