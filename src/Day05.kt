import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    fun part1(input: List<String>): Int {
        val (ranges, test) = input

        val fresh = ranges.split("\n").map {
            val (f, l) = it.split('-')

            f.toLong()..l.toLong()
        }.toSet()

        return test.split("\n").sumOf {
            val n = it.toLong()

            if (fresh.any { r -> n in r }) {
                1
            } else {
                0
            }
        }
    }

    fun part2(input: List<String>): Long {
        val (ranges, _) = input

        val merged = buildList {
            val sorted = ranges.split("\n").map {
                val (f, l) = it.split('-')
                f.toLong() to l.toLong()
            }.sortedWith { (x1, y1), (x2, y2) ->
                if (x1 != x2) {
                    x1.compareTo(x2)
                } else {
                    y1.compareTo(y2)
                }
            }
            var (currStart, currEnd) = sorted[0]
            for ((nextStart, nextEnd) in sorted.drop(1)) {
                if (currStart <= nextStart && nextEnd <= currEnd) {
                    continue
                }

                if (nextStart <= currEnd) {
                    currEnd = nextEnd
                } else {
                    add(currStart to currEnd)
                    currStart = nextStart
                    currEnd = nextEnd
                }
            }
            add(currStart to currEnd)
        }

        return merged.sumOf { (a, b) -> b - a + 1 }
    }

    val testInput = """
        3-5
        10-14
        16-20
        12-18

        1
        5
        8
        11
        17
        32
    """.trimIndent().split("\n\n")

    val input = Path("src/Day05.txt").readText().trim().split("\n\n")
    part1(input).println()
    part2(input).println()
}