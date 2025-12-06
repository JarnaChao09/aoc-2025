import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    fun part1(input: List<String>): Long {
        val parsed = input.map {
            it.trim().split(Regex(" +"))
        }

        fun List<List<Long>>.transpose(): List<List<Long>> {
            return buildList {
                repeat(this@transpose[0].size) { i ->
                    add(buildList {
                        repeat(this@transpose.size) { j ->
                            add(this@transpose[j][i])
                        }
                    })
                }
            }
        }

        val values = parsed.dropLast(1).map {
            it.map(String::toLong)
        }.transpose()
        val operations = parsed.last()

        return operations.mapIndexed { i, operation ->
            when (operation) {
                "+" -> values[i].sum()
                "*" -> values[i].reduce(Long::times)
                else -> 0L
            }
        }.sum()
    }

    fun part2(input: List<String>): Long {
        val operations = input.last().trim().split(Regex(" +"))

        println(operations.size)

        fun List<List<Char>>.transpose(): List<List<Char>> {
            return buildList {
                repeat(this@transpose[0].size) { i ->
                    add(buildList {
                        repeat(this@transpose.size) { j ->
                            add(this@transpose[j][i])
                        }
                    })
                }
            }
        }

        fun List<String>.split(regex: Regex): List<List<String>> {
            return buildList {
                val current = mutableListOf<String>()

                this@split.forEach {
                    if (regex.matches(it)) {
                        add(current.toList())
                        current.clear()
                    } else {
                        current.add(it)
                    }
                }
                add(current.toList())
            }
        }

        val length = input.maxOfOrNull(String::length) ?: error("No max length")
        val values = input
            .dropLast(1)
            .map {
                it.padEnd(length).toList()
            }
            .transpose()
            .map {
                it.joinToString(separator = "")
            }
            .split(Regex(" +"))

        return operations.mapIndexed { i, operation ->
            when (operation) {
                "+" -> values[i].sumOf { it.replace(" ", "").toLong() }
                "*" -> values[i].fold(1L) { acc, s -> acc * s.replace(" ", "").toLong()}
                else -> 0L
            }
        }.sum()
    }

    val testInput = """
          6 328  51 64 
         45 64  387 23 
        123 98  215 314
        *   +   *   +  
    """.trimIndent().lines()

    val input = Path("src/Day06.txt").readLines()
    part1(input).println()
    part2(input).println()
}