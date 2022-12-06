package day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

class Day2KtTest {

    @Test
    fun example1() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()

        val strategy = parseRounds(input) { _, character -> HandShape.parse(character) }
        val tournamentResult = calculateTournamentResult(strategy)
        assertEquals(15, tournamentResult.points)
    }

    @Test
    fun part1() {
        val input = readInput(2)

        val strategy = parseRounds(input) { _, character -> HandShape.parse(character) }
        val tournamentResult = calculateTournamentResult(strategy)
        assertEquals(15572, tournamentResult.points)
    }

    @Test
    fun example2() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()

        val strategy = parseRounds(input) { opponentMove, character -> pickMoveBasedOnWantedOutcome(opponentMove, WantedOutcome.parse(character))  }
        val tournamentResult = calculateTournamentResult(strategy)
        assertEquals(12, tournamentResult.points)
    }

    @Test
    fun part2() {
        val input = readInput(2)

        val strategy = parseRounds(input) { opponentMove, character -> pickMoveBasedOnWantedOutcome(opponentMove, WantedOutcome.parse(character))  }
        val tournamentResult = calculateTournamentResult(strategy)
        assertEquals(16098, tournamentResult.points)
    }

}
