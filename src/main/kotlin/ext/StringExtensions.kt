package ext

fun String.splitAtIndex(index: Int): Pair<String, String> {
    return Pair(this.substring(0, index), this.substring(index))
}

fun String.splitInTwo(): Pair<String, String> {
    return splitAtIndex(this.length / 2)
}
