package day4

data class ElfPair(val assignment1: IntRange, val assignment2: IntRange) {

    fun oneFullyContainsTheOther(): Boolean {
        return (assignment1.contains(assignment2.first) && assignment1.contains(assignment2.last))
                || (assignment2.contains(assignment1.first) && assignment2.contains(assignment1.last))
    }

    fun anyOverlap(): Boolean {
        return (assignment1.contains(assignment2.first) || assignment1.contains(assignment2.last))
                || (assignment2.contains(assignment1.first) || assignment2.contains(assignment1.last))
    }
}

fun parsePairs(input: String): List<ElfPair> {
    return input.lines()
        .map {
            val rangeStrings = it.split(",")
            val assigment1 = parseRange(rangeStrings[0])
            val assigment2 = parseRange(rangeStrings[1])
            ElfPair(assigment1, assigment2)
        }
}

fun parseRange(range: String): IntRange {
    val tokens = range.split("-")
    val rangeStart = tokens[0].toInt()
    val rangeEnd = tokens[1].toInt()
    return IntRange(rangeStart, rangeEnd)
}
