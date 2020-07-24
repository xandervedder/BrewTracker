package vedder.xander.brewtracker.pattern

class IntervalViewTypePattern(
    intervalPattern: String,
    viewFactoriesLength: Int,
    private val intervalSize: Int,
    private val defaultIndex: Int
) : ViewTypePattern() {

    private val indexList: List<Int> = this.compile(intervalPattern, viewFactoriesLength - 1)

    override fun get(position: Int): Int {
        val positionInPair = position % (this.intervalSize + this.indexList.size)

        // It's in the interval, we can grab the index from the pattern
        return if ((positionInPair - (this.intervalSize - 1)) > 0)
            this.indexList[positionInPair - this.intervalSize]
        else
            this.defaultIndex
    }
}
