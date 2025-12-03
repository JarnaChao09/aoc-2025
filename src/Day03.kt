import kotlin.time.measureTimedValue

fun main() {
    fun part1(input: List<String>): Int {
        var ret = 0
        input.forEach {
            var max = 0
            for (i in it.indices) {
                val f = it[i]
                for (j in (i + 1)..<it.length) {
                    val l = it[j]
                    val v = "$f$l".toInt()
                    if (v > max) {
                        max = v
                    }
                }
            }
            ret += max
        }

        return ret
    }

    fun part2(input: List<String>): Long {
        // fun perm(s: String): Sequence<Long> {
        //     return sequence {
        //         var prev = listOf("")
        //
        //         var i = 0
        //         while (i < s.length) {
        //             prev = buildList {
        //                 for (str in prev) {
        //                     val tmp = str + s[i]
        //                     if (str.length < 12) {
        //                         add(str)
        //                     }
        //                     if (tmp.length < 12) {
        //                         add(tmp)
        //                     }
        //                     if (str.length == 12) {
        //                         yield(str.toLong())
        //                     }
        //                     if (tmp.length == 12) {
        //                         yield(tmp.toLong())
        //                     }
        //                 }
        //                 i++
        //             }
        //         }
        //     }
        // }

        // shoutout to @sampersand from PLTD for this solution
        fun perm(s: String, size: Int = 12): String {
            if (size == 0) {
                return ""
            }

            val max = s.dropLast(size - 1).max()
            return max + perm(s.substring(s.indexOf(max) + 1), size - 1)
        }
        return input.sumOf {
            perm(it).toLong()
        }
    }

    val testInput = """
        987654321111111
        811111111111119
        234234234234278
        818181911112111
    """.trimIndent().lines()

    val input = readInput("Day03")
    measureTimedValue { part1(input) }.println()
    measureTimedValue { part2(input) }.println()
}