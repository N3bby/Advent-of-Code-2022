package day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

class Day3Test {

    @Test
    fun example1() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val rucksacks = parseRucksacks(input)
        val sum = rucksacks.sumOfPrioritiesOfItemsInBothCompartments()

        assertEquals(157, sum)
    }

    @Test
    fun part1() {
        val input = readInput(3)

        val rucksacks = parseRucksacks(input)
        val sum = rucksacks.sumOfPrioritiesOfItemsInBothCompartments()

        assertEquals(8072, sum)
    }

    @Test
    fun example2() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val rucksacks = parseRucksacks(input)
        val sum = rucksacks.sumOfBadgePriorities()

        assertEquals(70, sum)
    }

    @Test
    fun part2() {
        val input = readInput(3)

        val rucksacks = parseRucksacks(input)
        val sum = rucksacks.sumOfBadgePriorities()

        assertEquals(2567, sum)
    }
}
