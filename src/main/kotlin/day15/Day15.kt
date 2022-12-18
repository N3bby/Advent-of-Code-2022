package day15

import day15.Step.X
import day15.Step.Y
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

data class Bounds(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int)

data class Position<T>(val x: T, val y: T) {
}

fun Position<Int>.manhattanDistanceFrom(other: Position<Int>): Int {
    return abs(x - other.x) + abs(y - other.y)
}

enum class Step {
    X,
    Y
}

// Only works for diagonals!!!
fun Position<Int>.positionsTo(other: Position<Int>, firstStep: Step): List<Position<Int>> {
    val positions = mutableListOf(this)
    var step = firstStep

    val xStep = if(other.x > this.x) 1 else -1
    val yStep = if(other.y > this.y) 1 else -1

    while (positions.last() != other) {
        when(step) {
            X -> positions.add(Position(positions.last().x + xStep, positions.last().y))
            Y -> positions.add(Position(positions.last().x, positions.last().y + yStep))
        }
        step = if (step == X) Y else X
    }
    return positions
}

data class Sensor(val position: Position<Int>, val closestBeacon: Position<Int>) {

    private fun getCheckedRange(): Int {
        return position.manhattanDistanceFrom(closestBeacon)
    }

    val smallestXPos: Int get() = position.x - getCheckedRange()
    val largestXPos: Int get() =  position.x + getCheckedRange()
    val smallestYPos: Int get() =  position.y - getCheckedRange()
    val largestYPos: Int get() =  position.y + getCheckedRange()

    fun isPositionChecked(position: Position<Int>): Boolean {
        return position.manhattanDistanceFrom(this.position) <= getCheckedRange()
    }

    fun getPositionsSkirtingRange(): Set<Position<Int>> {
        val rightCorner = Position(largestXPos + 1, position.y)
        val leftCorner = Position(smallestXPos - 1, position.y)
        val topCorner = Position(position.x, largestYPos + 1)
        val bottomCorner = Position(position.x, smallestYPos - 1)

        return setOf(
            *rightCorner.positionsTo(topCorner, Y).toTypedArray(),
            *topCorner.positionsTo(leftCorner, X).toTypedArray(),
            *leftCorner.positionsTo(bottomCorner, Y).toTypedArray(),
            *bottomCorner.positionsTo(rightCorner, X).toTypedArray(),
        )
    }
}

fun parseInput(input: String): List<Sensor> {
    return input.lines()
        .map { line ->
            val regex = Regex(".*x=(.*), y=(.*):.*x=(.*), y=(.*).*")
            val result = regex.matchEntire(line) ?: throw Error("Invalid line: '${line}'")
            val sensorPosition = Position(
                result.groups[1]!!.value.toInt(),
                result.groups[2]!!.value.toInt()
            )
            val beaconPosition = Position(
                result.groups[3]!!.value.toInt(),
                result.groups[4]!!.value.toInt()
            )
            Sensor(sensorPosition, beaconPosition)
        }
}

fun countCheckedPositionsOnRow(sensors: List<Sensor>, row: Int): Int {
    val bounds = getBounds(sensors)

    val positions = (bounds.minX .. bounds.maxX).map { Position(it, row) }

    return positions
        .filter { sensors.none { sensor -> sensor.closestBeacon == it } }
        .filter { sensors.any { sensor -> sensor.isPositionChecked(it) } }.size
}


fun findBeacon(sensors: List<Sensor>, minCoord: Int, maxCoord: Int): Position<Int> {
    return sensors
        .parallelStream()
        .flatMap { it.getPositionsSkirtingRange().stream() }
        .filter { it.x in minCoord..maxCoord && it.y in minCoord .. maxCoord }
        .filter { sensors.all { sensor -> !sensor.isPositionChecked(it) } }
        .findAny().orElseThrow()
}

fun calculateTuningFrequency(beaconPosition: Position<Int>): Long {
    return beaconPosition.x * 4_000_000L + beaconPosition.y
}

fun getBounds(sensors: List<Sensor>): Bounds {
    return Bounds(
        sensors.minByOrNull { it.smallestXPos }!!.smallestXPos,
        sensors.maxByOrNull { it.largestXPos }!!.largestXPos,
        sensors.minByOrNull { it.smallestYPos }!!.smallestYPos,
        sensors.maxByOrNull { it.largestYPos }!!.largestYPos
    )
}
