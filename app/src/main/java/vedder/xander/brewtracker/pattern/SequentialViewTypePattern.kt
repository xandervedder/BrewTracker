package vedder.xander.brewtracker.pattern

class SequentialViewTypePattern(pattern: String, viewFactoriesLength: Int) : ViewTypePattern {

    private val stringPattern = pattern
    private val maxIndexNumber = viewFactoriesLength - 1
    private val indexList: MutableList<Int> = ArrayList()

    override fun compile(): ViewTypePattern {
        for (num in this.stringPattern.split(STRING_DELIMITER)) {
            val index = num.toInt()
            if (index > this.maxIndexNumber)
                throw IllegalArgumentException("Index cannot be higher than size of viewFactories")
            else
                this.indexList.add(num.toInt())
        }
        return this;
    }

    override fun get(position: Int, datasetSize: Int): Int = this.indexList[position % this.indexList.size]
}
