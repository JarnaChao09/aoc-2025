fun main() {
    infix fun <T> Set<T>.xor(that: Set<T>): Set<T> = (this - that) + (that - this)

    fun part1(input: List<String>): Int {
        fun solve1(line: String): Int {
            val data = line.split(" ")

            val goals = buildSet {
                data.first().let {
                    it.substring(1, it.length - 1).forEachIndexed { index, c ->
                        if (c == '#') {
                            add(index)
                        }
                    }
                }
            }
            val jolts = data.last()
            val buttons = data.drop(1).dropLast(1)

            val moves = buildList {
                for (button in buttons) {
                    val buttonActivations = button.substring(1, button.length - 1).split(",").map(String::toInt)

                    add(buttonActivations)
                }
            }

            val queue = ArrayDeque<Pair<Set<Int>, Int>>()
            queue.add(emptySet<Int>() to 0)
            val seen = mutableSetOf<Set<Int>>()
            while (queue.isNotEmpty()) {
                val (current, steps) = queue.removeFirst()
                if (current == goals) {
                    return steps
                }
                for (move in moves) {
                    val newSet = current xor move.toSet()
                    if (newSet in seen) {
                        continue
                    }
                    seen.add(newSet)
                    queue.add(newSet to (steps + 1))
                }
            }
            return (-1).also { println("somehow made it out of the while loop") }
        }
        return input.sumOf(::solve1)
    }

    fun part2(input: List<String>): Long {
        TODO()
    }

    val testInput = """
        [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
        [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
        [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
    """.trimIndent().lines()

    val input = readInput("Day10")
    part1(input).println()
    // part2(input).println()
}