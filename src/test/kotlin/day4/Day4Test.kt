package day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

class Day4Test {

    @Test
    fun example1() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()

        val pairs = parsePairs(input);
        assertEquals(2, pairs.filter{ it.oneFullyContainsTheOther() }.size)
    }

    @Test
    fun part1() {
        val input = readInput(4)

        val pairs = parsePairs(input);
        assertEquals(567, pairs.filter{ it.oneFullyContainsTheOther() }.size)
    }

    @Test
    fun example2() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent()

        val pairs = parsePairs(input);
        assertEquals(4, pairs.filter{ it.anyOverlap() }.size)
    }

    @Test
    fun part2() {
        val input = readInput(4)

        val pairs = parsePairs(input);
        assertEquals(907, pairs.filter{ it.anyOverlap() }.size)
    }

}
