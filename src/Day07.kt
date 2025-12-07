import kotlin.time.measureTimedValue

fun main() {
    fun part1(input: List<String>): Int {
        var cursors = setOf(input[0].indexOf('S'))
        var c = 0

        input.drop(1).forEach {
            cursors = buildSet {
                for (cursor in cursors) {
                    if (it[cursor] == '^') {
                        add(cursor - 1)
                        add(cursor + 1)
                        c++
                    } else {
                        add(cursor)
                    }
                }
            }
        }

        return c
    }

    fun part2(input: List<String>): Long {
        var cursors = mapOf(input[0].indexOf('S') to 1L)

        input.drop(1).forEach {
            cursors = buildMap {
                for ((cursor, timelines) in cursors) {
                    if (it[cursor] == '^') {
                        put(cursor - 1, getOrElse(cursor - 1) { 0L } + timelines)
                        put(cursor + 1, getOrElse(cursor + 1) { 0L } + timelines)
                    } else {
                        put(cursor, getOrElse(cursor) { 0L } + timelines)
                    }
                }
            }
        }

        return cursors.values.sum()
    }

    fun both(input: List<String>): Pair<Int, Long> {
        var cursors = mapOf(input[0].indexOf('S') to 1L)
        var c = 0

        input.drop(1).forEach {
            cursors = buildMap {
                for ((cursor, timelines) in cursors) {
                    if (it[cursor] == '^') {
                        put(cursor - 1, getOrElse(cursor - 1) { 0L } + timelines)
                        put(cursor + 1, getOrElse(cursor + 1) { 0L } + timelines)
                        c++
                    } else {
                        put(cursor, getOrElse(cursor) { 0L } + timelines)
                    }
                }
            }
        }

        return c to cursors.values.sum()
    }

    val testInput = """
        .......S.......
        ...............
        .......^.......
        ...............
        ......^.^......
        ...............
        .....^.^.^.....
        ...............
        ....^.^...^....
        ...............
        ...^.^...^.^...
        ...............
        ..^...^.....^..
        ...............
        .^.^.^.^.^...^.
        ...............
    """.trimIndent().lines()

    val input = readInput("Day07")
    // part1(input).println()
    // part2(input).println()
    measureTimedValue { both(input) }.println()
}