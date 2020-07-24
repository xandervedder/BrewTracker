package vedder.xander.brewtracker.pattern

class SequentialViewTypePattern(pattern: String, viewFactoriesLength: Int) : ViewTypePattern() {

    private val indexList: List<Int> = this.compile(pattern, viewFactoriesLength - 1)

    override fun get(position: Int): Int = this.indexList[position % this.indexList.size]
}
