package vedder.xander.brewtracker.pattern

const val STRING_DELIMITER = ":"

abstract class ViewTypePattern {

    protected fun compile(pattern: String, maxIndex: Int): List<Int> {
        val indexList: MutableList<Int> = ArrayList()
        for (num in pattern.split(STRING_DELIMITER)) {
            val index = num.toInt()
            if (index > maxIndex)
                throw IllegalArgumentException("Index cannot be higher than maxIndex: $maxIndex")
            else
                indexList.add(num.toInt())
        }
        return indexList
    }

    abstract fun get(position: Int): Int
}
