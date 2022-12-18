package day15

import day15.Step.X
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

class Day15KtTest {

    @Test
    fun example1() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent()

        val sensors = parseInput(input)

        assertEquals(26, countCheckedPositionsOnRow(sensors, 10))
    }

    @Test
    fun part1() {
        val input = readInput(15)
        val sensors = parseInput(input)

        assertEquals(4793062, countCheckedPositionsOnRow(sensors, 2_000_000))
    }

    @Test
    fun example2() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent()

        val sensors = parseInput(input)

        val beaconPosition = findBeacon(sensors, 0, 20)
        assertEquals(Position(14, 11), beaconPosition)
        assertEquals(56000011, calculateTuningFrequency(beaconPosition))
    }

    @Test
    fun part2() {
        val input = readInput(15)
        val sensors = parseInput(input)

        val beaconPosition = findBeacon(sensors, 0, 4_000_000)
        assertEquals(Position(2706598, 3253551), beaconPosition)
        assertEquals(10826395253551, calculateTuningFrequency(beaconPosition))
    }

    @Test
    fun position_positionsTo() {
        val pos = Position(0, 0)
        val to = Position(3, 3)
        val expected = listOf(
            pos,
            Position(1, 0),
            Position(1, 1),
            Position(2, 1),
            Position(2, 2),
            Position(3, 2),
            to
        )
        assertEquals(expected, pos.positionsTo(to, X))
    }

    @Test
    fun sensor_findPositionsSkirtingRange() {
        val sensor = Sensor(Position(0, 0), Position(1, 0))
        val positions = sensor.getPositionsSkirtingRange()
        assertEquals(setOf(
            Position(2, 0),
            Position(2, 1),
            Position(1, 1),
            Position(1, 2),
            Position(0, 2),
            Position(-1, 2),
            Position(-1, 1),
            Position(-2, 1),
            Position(-2, 0),
            Position(-2, -1),
            Position(-1, -1),
            Position(-1, -2),
            Position(0, -2),
            Position(1, -2),
            Position(1, -1),
            Position(2, -1),
        ), positions)
    }
}