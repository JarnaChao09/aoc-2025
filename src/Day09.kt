import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Long {
        val points = input.map { it.split(",").map(String::toLong) }

        var max = 0L
        for (p1 in points) {
            val (x1, y1) = p1
            for (p2 in points) {
                val (x2, y2) = p2

                val area = (abs(x2 - x1) + 1) * (abs(y2 - y1) + 1)

                if (area > max) {
                    max = area
                }
            }
        }

        return max
    }

    fun part2(input: List<String>): Long {
        val points = input.map { it.split(",").map(String::toLong) }

        var max = 0L

        for ((x1, y1) in points) {
            for ((x2, y2) in points) {
                if (x1 > x2 && y1 > y2) {
                    continue
                }

                val bx1 = min(x1, x2)
                val bx2 = max(x1, x2)
                val by1 = min(y1, y2)
                val by2 = max(y1, y2)

                var found = false
                for ((index, point) in points.withIndex()) {
                    val (lx1, ly1) = point
                    val (lx2, ly2) = points[(index + 1) % points.size]

                    val cond1 = max(lx1, lx2) <= bx1
                    val cond2 = bx2 <= min(lx1, lx2)
                    val cond3 = max(ly1, ly2) <= by1
                    val cond4 = by2 <= min(ly1, ly2)
                    if (!(cond1 || cond2 || cond3 || cond4)) {
                        found = true
                        break
                    }
                }
                if (!found) {
                    max = max(max, (bx2 - bx1 + 1) * (by2 - by1 + 1))
                }
            }
        }

        return max
    }

    val testInput = """
        7,1
        11,1
        11,7
        9,7
        9,5
        2,5
        2,3
        7,3
    """.trimIndent().lines()

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}