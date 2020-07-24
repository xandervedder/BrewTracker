package vedder.xander.brewtracker.pattern

class HeaderViewTypePattern(
    headerPattern: String,
    bodyPattern: String,
    viewFactoriesLength: Int
) : ViewTypePattern() {

    private val headerIndexList: List<Int> = this.compile(headerPattern, viewFactoriesLength - 1)
    private val bodyIndexList: List<Int> = this.compile(bodyPattern, viewFactoriesLength - 1)

    override fun get(position: Int): Int {
        val divided = position / (headerIndexList.size - 1)
        return if (divided < 1 || divided == 1)
            headerIndexList[position]
        else
            bodyIndexList[(position - headerIndexList.size) % bodyIndexList.size]
    }
}
