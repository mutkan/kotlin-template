package techprague.nodes.dk.data

import com.squareup.moshi.Moshi
import org.junit.Test
import techprague.nodes.dk.data.models.body.MatchById

class RestModuleTest {

    @Test
    fun test() {
        val test = this::class.java.getResource("match.json").readText()
        val moshi = Moshi.Builder().build()
        val match = moshi.adapter(MatchById::class.java).fromJson(test)

        print(test)
    }
}