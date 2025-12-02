fun main() {
    fun part1(input: String): Long {
        val ranges = input.split(",").map {
            val (f, l) = it.split("-")

            f to l
        }

        var ret = 0L

        ranges.forEach { (f, l) ->
            val first = f.toLong()
            val last = l.toLong()

            for(i in first..last) {
                val s = i.toString()

                if (s.length % 2 != 0) {
                    continue
                } else {
                    val length = s.length / 2
                    if (s.take(length) == s.drop(length)) {
                        ret += i
                    }
                }
            }
        }

        return ret
    }

    fun part2(input: String): Long {
        val ranges = input.split(",").map {
            val (f, l) = it.split("-")

            f to l
        }

        val regex = Regex("^(\\d+)\\1+")

        var ret = 0L

        ranges.forEach { (f, l) ->
            val first = f.toLong()
            val last = l.toLong()

            for(i in first..last) {
                val s = i.toString()

                if (regex.matches(s)) {
                    ret += i
                }
            }
        }

        return ret
    }

    val testInput = """
        11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124
    """.trimIndent().lines()

    val input = readInput("Day02")
    part1(input[0]).println()
    part2(input[0]).println()
}