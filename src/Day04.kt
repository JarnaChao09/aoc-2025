fun main() {
    fun parse(input: List<String>): List<List<Int>> {
        return buildList {
            input.forEach { line ->
                add(
                    line.map {
                        if (it == '@') 1 else 0
                    }
                )
            }
        }
    }

    fun List<List<Int>>.safeIndex(row: Int, col: Int): Int {
        if (row !in this.indices || col !in this[row].indices) {
            return 0
        }

        return this[row][col]
    }

    fun part1(input: List<List<Int>>): Int {
        var c = 0

        for (i in input.indices) {
            for (j in input[i].indices) {
                val home = input[i][j]
                if (home != 0) {
                    val nw = input.safeIndex(i - 1, j - 1)
                    val n = input.safeIndex(i - 1, j)
                    val ne = input.safeIndex(i - 1, j + 1)
                    val w = input.safeIndex(i, j - 1)
                    val e = input.safeIndex(i, j + 1)
                    val sw = input.safeIndex(i + 1, j - 1)
                    val s = input.safeIndex(i + 1, j)
                    val se = input.safeIndex(i + 1, j + 1)

                    val sum = nw + n + ne + w + e + sw + s + se
                    if (sum < 4) {
                        c++
                    }
                }
            }
        }

        return c
    }

    fun part2(input: List<List<Int>>): Int {
        fun solve(grid: List<List<Int>>): Pair<Int, Set<Pair<Int, Int>>> {
            val remove = mutableSetOf<Pair<Int, Int>>()
            var c = 0

            for (i in grid.indices) {
                for (j in grid[i].indices) {
                    val home = grid[i][j]
                    if (home != 0) {
                        val nw = grid.safeIndex(i - 1, j - 1)
                        val n = grid.safeIndex(i - 1, j)
                        val ne = grid.safeIndex(i - 1, j + 1)
                        val w = grid.safeIndex(i, j - 1)
                        val e = grid.safeIndex(i, j + 1)
                        val sw = grid.safeIndex(i + 1, j - 1)
                        val s = grid.safeIndex(i + 1, j)
                        val se = grid.safeIndex(i + 1, j + 1)

                        val sum = nw + n + ne + w + e + sw + s + se
                        if (sum < 4) {
                            remove.add(i to j)
                            c++
                        }
                    }
                }
            }

            return c to remove
        }

        fun fixGrid(grid: List<List<Int>>, remove: Set<Pair<Int, Int>>): List<List<Int>> {
            return grid.mapIndexed { i, row ->
                row.mapIndexed { j, col ->
                    if (remove.contains(i to j)) {
                        0
                    } else {
                        col
                    }
                }
            }
        }

        var c = input

        var ret = 0

        var prev = 0

        do {
            val (r, s) = solve(c)
            ret += r
            prev = r
            c = fixGrid(c, s)
        } while (prev != 0)

        return ret
    }

    val testInput = """
        ..@@.@@@@.
        @@@.@.@.@@
        @@@@@.@.@@
        @.@@@@..@.
        @@.@@@@.@@
        .@@@@@@@.@
        .@.@.@.@@@
        @.@@@.@@@@
        .@@@@@@@@.
        @.@.@@@.@.
    """.trimIndent().lines()

    val input = readInput("Day04")
    part1(parse(input)).println()
    part2(parse(input)).println()
}