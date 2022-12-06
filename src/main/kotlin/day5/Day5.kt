package day5

import ext.reversedPerLine
import ext.stackOf
import ext.transpose
import java.util.*

data class CrateStack(val identifier: Int, val stack: Stack<Char>)

open class CargoCrane9000(val stacks: List<CrateStack>, val moves: List<Move>) {

    fun executeMoves() {
        moves.forEach(this::executeMove)
    }

    private fun executeMove(move: Move) {
        val fromStack = getCrate(move.fromStack)
        val toStack = getCrate(move.toStack)
        repeat(move.amount) {
            val crate = fromStack.stack.pop()
            toStack.stack.add(crate)
        }
    }

    private fun getCrate(identifier: Int): CrateStack {
        return stacks.find { it.identifier == identifier }
            ?: throw Error("Could not find crate with identifier $identifier")
    }

    fun getTopCrateOfEveryStack(): String {
        return stacks.map { it.stack.peek() }.joinToString("")
    }

}

data class Move(val fromStack: Int, val toStack: Int, val amount: Int)

fun parseInput(input: String): Pair<List<CrateStack>, List<Move>> {
    val parts = input.split("\n\n")
    val stacksInput = parts[0]
    val movesInput = parts[1]

    return Pair(
        parseCrateStacks(stacksInput),
        parseMoves(movesInput)
    )
}

fun parseCrateStacks(stacksInput: String): List<CrateStack> {
    val stackLines = stacksInput
        .transpose()
        .reversedPerLine()
        .lines()
        .filterIndexed { index, _ -> index % 4 == 1 }

    return stackLines.map { line ->
        val tokens = line.split("").filter { it.isNotBlank() }

        val stackIdentifier = tokens[0].toInt()
        val crates = tokens.subList(1, tokens.size).map { it[0] }
        val stack = stackOf(crates)

        CrateStack(stackIdentifier, stack)
    }
}

fun parseMoves(movesInput: String): List<Move> {
    val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")

    return movesInput
        .lines()
        .filter { it.isNotBlank() }
        .map { regex.matchEntire(it) ?: throw Error("Line did not match our pattern: '$it'") }
        .map { regexResult -> regexResult.groupValues.drop(1).map { it.toInt() } }
        .map { Move(it[1], it[2], it[0]) }
}
