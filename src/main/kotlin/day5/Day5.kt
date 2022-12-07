package day5

import ext.*
import java.util.*

data class CrateStack(val identifier: Int, val stack: Stack<Char>)

open class CargoCrane9000(val stacks: List<CrateStack>, val moves: List<Move>) {

    fun executeMoves() {
        moves.forEach(this::executeMove)
    }

    protected open fun executeMove(move: Move) {
        val fromStack = getCrate(move.fromStack)
        val toStack = getCrate(move.toStack)
        repeat(move.amount) {
            val crate = fromStack.stack.pop()
            toStack.stack.add(crate)
        }
    }

    protected fun getCrate(identifier: Int): CrateStack {
        return stacks.find { it.identifier == identifier }
            ?: throw Error("Could not find crate with identifier $identifier")
    }

    fun getTopCrateOfEveryStack(): String {
        return stacks.map { it.stack.peek() }.joinToString("")
    }

}

class CargoCrane9001(stacks: List<CrateStack>, moves: List<Move>): CargoCrane9000(stacks, moves) {

    override fun executeMove(move: Move) {
        val fromStack = getCrate(move.fromStack)
        val toStack = getCrate(move.toStack)

        val itemsInOriginalOrder = IntRange(1, move.amount).map { fromStack.stack.pop() }.reversed()
        toStack.stack.addAll(itemsInOriginalOrder)
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
        .steppedBy(4, 1)

    return stackLines.map { line ->
        val (identifier, crates) = line
            .trim()
            .splitAtIndex(1)

        CrateStack(
            identifier.toInt(),
            stackOf(crates.toList())
        )
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
