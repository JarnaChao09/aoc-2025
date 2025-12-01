fun main() {
    fun part1(input: List<String>): Int {
        var s = 50
        var c = 0

        input.forEach {
            val dir = it[0]
            val num = it.drop(1).toInt()

            if (dir == 'L') {
                s -= num
                s = s.mod(100)
            } else {
                s += num
                s = s.mod(100)
            }

            if (s == 0) {
                c++
            }
        }
        return c
    }

    fun part2(input: List<String>): Int {
        var s = 50
        var c = 0
        var wasZero = false

        input.forEach {
            val dir = it[0]
            val num = it.drop(1).toInt()

            val timesPastZero = num / 100
            c += timesPastZero

            val rest = num % 100

            if (dir == 'L') {
                s -= rest
                if (s < 0 && !wasZero) {
                    c++
                }
                s = s.mod(100)
            } else {
                s += rest
                if (s > 100) {
                    c++
                }
                s = s.mod(100)
            }

            if (s == 0) {
                c++
                wasZero = true
            } else {
                wasZero = false
            }
        }
        return c
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
