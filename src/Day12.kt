import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    fun part1(input: List<String>): Int {
        val shapes = input.take(6).map {
            it.split("\n").drop(1).sumOf { row -> row.count { c -> c == '#'} }
        }
        val tests = input.drop(6).first().split("\n").map {
            val (size, counts) = it.split(": ")
            val (sizeX, sizeY) = size.split("x").map(String::toLong)

            Triple(sizeX, sizeY, counts.split(" ").map(String::toLong))
        }

        return tests.filter { (x, y, boxes) ->
            x * y > boxes.zip(shapes).sumOf { (numBoxes, size) -> numBoxes * size}
        }.size
    }

    val testInput = """
        0:
        ###
        ##.
        ##.

        1:
        ###
        ##.
        .##

        2:
        .##
        ###
        ##.

        3:
        ##.
        ###
        ##.

        4:
        ###
        #..
        ###

        5:
        ###
        .#.
        ###

        4x4: 0 0 0 0 2 0
        12x5: 1 0 1 0 2 2
        12x5: 1 0 1 0 3 2
    """.trimIndent().lines()

    val input = Path("src/Day12.txt").readText().split("\n\n")
    part1(input).println()
}