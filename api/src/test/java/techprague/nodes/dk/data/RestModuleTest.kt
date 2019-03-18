package techprague.nodes.dk.data

import org.junit.Assert.*
import org.junit.Test

class RestModuleTest {

    @Test
    fun test() {
        val test = this::class.java.getResource("match.json").readText()
        print(test)
    }
}