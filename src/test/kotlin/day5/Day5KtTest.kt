package day5

import ext.stackOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.readInput

class Day5KtTest {

    @Test
    fun example1() {
        val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """.trimIndent()

        val parsed = parseInput(input)
        val cargoCrane = CargoCrane9000(parsed.first, parsed.second)

        assertThat(cargoCrane).usingRecursiveComparison().isEqualTo(
            CargoCrane9000(
                listOf(
                    CrateStack(1, stackOf('Z', 'N')),
                    CrateStack(2, stackOf('M', 'C', 'D')),
                    CrateStack(3, stackOf('P'))
                ),
                listOf(
                    Move(2, 1, 1),
                    Move(1, 3, 3),
                    Move(2, 1, 2),
                    Move(1, 2, 1)
                )
            )
        )

        cargoCrane.executeMoves()
        assertEquals("CMZ", cargoCrane.getTopCrateOfEveryStack())
    }

    @Test
    fun part1() {
        val input = readInput(5, false)

        val parsed = parseInput(input)
        val cargoCrane = CargoCrane9000(parsed.first, parsed.second)

        cargoCrane.executeMoves()
        assertEquals("RLFNRTNFB", cargoCrane.getTopCrateOfEveryStack())
    }

    @Test
    fun example2() {
        val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """.trimIndent()

        val parsed = parseInput(input)
        val cargoCrane = CargoCrane9001(parsed.first, parsed.second)

        cargoCrane.executeMoves()
        assertEquals("MCD", cargoCrane.getTopCrateOfEveryStack())
    }

    @Test
    fun part2() {
        val input = readInput(5, false)

        val parsed = parseInput(input)
        val cargoCrane = CargoCrane9001(parsed.first, parsed.second)

        cargoCrane.executeMoves()
        assertEquals("RLFNRTNFB", cargoCrane.getTopCrateOfEveryStack())
    }

}
