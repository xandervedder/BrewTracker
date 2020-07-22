package vedder.xander.brewtracker.pattern

class IntervalViewTypePattern(
    private val intervalPattern: String,
    private val intervalSize: Int,
    private val viewFactoriesLength: Int,
    private val defaultIndex: Int
) : ViewTypePattern {

    private val indexList: MutableList<Int> = ArrayList()

    override fun compile(): ViewTypePattern {
        // TODO: Static method somewhere (Util method, or something similar):
        for (num in this.intervalPattern.split(STRING_DELIMITER)) {
            val index = num.toInt()
            if (index > this.viewFactoriesLength - 1)
                throw IllegalArgumentException("Index cannot be higher than size of viewFactories")
            else
                this.indexList.add(num.toInt())
        }
        return this;
    }

    override fun get(position: Int): Int {
        val positionInPair = position % (this.intervalSize + this.indexList.size)

        // It's in the interval, we can grab the index from the pattern
        return if ((positionInPair - (this.intervalSize - 1)) > 0)
            this.indexList[positionInPair - this.intervalSize]
        else
            this.defaultIndex
    }
}
