package day4

import ext.contains
import ext.overlaps

data class ElfPair(val assignment1: IntRange, val assignment2: IntRange) {

    fun oneAssignmentFullyCoversTheOther(): Boolean {
        return assignment1.contains(assignment2) || assignment2.contains(assignment1)
    }

    fun assignmentsOverlap(): Boolean {
        return assignment1.overlaps(assignment2)
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
