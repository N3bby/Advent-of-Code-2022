package day1

import util.readFile

fun main() {
    val lines = readFile("input/day1.txt")
    val parsedInput = parseInput(lines)

    val largestTotalOfCalories = getLargestTotalOfCalories(parsedInput)
    val top3TotalOfCalories = getTop3TotalOfCalories(parsedInput)

    println("Part 1: $largestTotalOfCalories")
    println("Part 2: $top3TotalOfCalories")
}

fun parseInput(lines: String): List<List<Int>> {
    return lines.split("\n\n")
        .map { groupOfLines ->
            groupOfLines.split("\n").filter { it.isNotBlank() }
                .map { line -> line.toInt() }
        }
}

fun getLargestTotalOfCalories(calories: List<List<Int>>): Int {
    return calories.maxOf { it.sum() }
}

fun getTop3TotalOfCalories(calories: List<List<Int>>): Int {
    return calories.map { it.sum() }
        .sorted()
        .reversed()
        .subList(0, 3)
        .sum()
}
