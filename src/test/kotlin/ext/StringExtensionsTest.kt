package ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringExtensionsTest {

    @Test
    fun string_splitInTwo() {
        Assertions.assertEquals("abcd".splitInTwo(), Pair("ab", "cd"))
    }

}
