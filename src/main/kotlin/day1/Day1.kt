package day1

import util.readFile

typealias FoodCaloriesList = List<Int>

fun main() {
    val lines = readFile("input/day1.txt")
    val parsedInput = parseInput(lines)

    val largestTotalOfCalories = getLargestTotalOfCalories(parsedInput)
    val top3TotalOfCalories = getTop3TotalOfCalories(parsedInput)

    println("Part 1: $largestTotalOfCalories")
    println("Part 2: $top3TotalOfCalories")
}

fun parseInput(lines: String): List<FoodCaloriesList> {
    return lines.split("\n\n")
        .map { linesPerElf ->
            linesPerElf.split("\n")
                .filter { it.isNotBlank() }
                .map { caloriesLine -> caloriesLine.toInt() }
        }
}

fun getLargestTotalOfCalories(caloriesListPerElf: List<FoodCaloriesList>): Int {
    return caloriesListPerElf.maxOf { it.sum() }
}

fun getTop3TotalOfCalories(caloriesListPerElf: List<FoodCaloriesList>): Int {
    return caloriesListPerElf.map { it.sum() }
        .sorted()
        .reversed()
        .subList(0, 3)
        .sum()
}
