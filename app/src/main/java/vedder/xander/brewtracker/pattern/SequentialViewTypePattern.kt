package vedder.xander.brewtracker.pattern

class SequentialViewTypePattern(private val pattern: String, viewFactoriesLength: Int) : ViewTypePattern {

    private val maxIndexNumber = viewFactoriesLength - 1
    private val indexList: MutableList<Int> = ArrayList()

    override fun compile(): ViewTypePattern {
        for (num in this.pattern.split(STRING_DELIMITER)) {
            val index = num.toInt()
            if (index > this.maxIndexNumber)
                throw IllegalArgumentException("Index cannot be higher than size of viewFactories")
            else
                this.indexList.add(num.toInt())
        }
        return this;
    }

    override fun get(position: Int): Int = this.indexList[position % this.indexList.size]
}
