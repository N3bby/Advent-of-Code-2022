package day2

val DRAW_POINTS = 3
val WIN_POINTS = 6

sealed class HandShape {
    abstract val winsAgainst: HandShape
    abstract val losesAgainst: HandShape
    abstract val points: Int

    object Rock : HandShape() {
        override val winsAgainst = Scissors
        override val losesAgainst = Paper
        override val points = 1
    }

    object Paper : HandShape() {
        override val winsAgainst = Rock
        override val losesAgainst = Scissors
        override val points = 2
    }

    object Scissors : HandShape() {
        override val winsAgainst = Paper
        override val losesAgainst = Rock
        override val points = 3
    }

    companion object {
        fun parse(character: String): HandShape {
            return when(character) {
                "A", "X" -> Rock
                "B", "Y" -> Paper
                "C", "Z" -> Scissors
                else -> throw java.lang.IllegalArgumentException("Unknown character $character")
            }
        }
    }
}

data class Round(val opponentMove: HandShape, val myMove: HandShape) {
    fun calculatePoints(): Int {
        return if(myMove == opponentMove) {
            myMove.points + DRAW_POINTS
        } else if(myMove.winsAgainst == opponentMove) {
            myMove.points + WIN_POINTS
        } else {
            myMove.points
        }
    }
}

data class TournamentResult(val points: Int)

fun parseRounds(input: String, myMoveStrategy: (opponentMove: HandShape, character: String) -> HandShape): List<Round> {
    return input
        .lines()
        .map { line ->
            val tokens = line.split(" ")
            val opponentMove = HandShape.parse(tokens[0])
            Round(opponentMove, myMoveStrategy(opponentMove, tokens[1]))
        }
}

fun calculateTournamentResult(round: List<Round>): TournamentResult {
    return TournamentResult(round.map(Round::calculatePoints).sum())
}

enum class WantedOutcome(private val character: String) {
    WIN("Z"),
    LOSE("X"),
    DRAW("Y");

    companion object {
        fun parse(character: String): WantedOutcome {
            return values().find { it.character == character}?: throw IllegalArgumentException("Unknown character $character")
        }
    }
}

fun pickMoveBasedOnWantedOutcome(opponentMove: HandShape, wantedOutcome: WantedOutcome): HandShape {
    return when(wantedOutcome) {
        WantedOutcome.WIN -> opponentMove.losesAgainst
        WantedOutcome.LOSE -> opponentMove.winsAgainst
        WantedOutcome.DRAW -> opponentMove
    }
}
