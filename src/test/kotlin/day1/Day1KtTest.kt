package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1KtTest {

    @Test
    fun example1() {
        val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()

        val result = getLargestTotalOfCalories(parseInput(input))
        assertEquals(24000, result)

        val result2 = getTop3TotalOfCalories(parseInput(input))
        assertEquals(result2, 45000)
    }

}
