import java.util.PriorityQueue

class UnionFind(n: Int) {
    val parents = MutableList(n) { it }
    val sizes = MutableList(n) { 1 }

    fun find(x: Int): Int {
        if (x == parents[x]) {
            return x
        }

        return find(parents[x]).also {
            parents[x] = it
        }
    }

    fun union(x: Int, y: Int) {
        val a = find(x)
        val b = find(y)
        if (a == b) {
            return
        }

        if (sizes[a] <= sizes[b]) {
            parents[a] = b
            sizes[b] += sizes[a]
        } else {
            parents[b] = a
            sizes[a] += sizes[b]
        }
    }
}

fun main() {
    fun Long.square(): Long = this * this
    fun part1(input: List<String>): Long {
        val data = input.map { it.split(",").map(String::toLong) }
        val n = data.size

        val heap = PriorityQueue<Triple<Long, Int, Int>>(compareBy { it.first })
        for (i in 0 until n) {
            val (x1, y1, z1) = data[i]
            for (j in i + 1 until n) {
                val (x2, y2, z2) = data[j]
                val dist = (x1 - x2).square() + (y1 - y2).square() + (z1 - z2).square()
                heap.offer(Triple(dist, i, j))
            }
        }

        val dsu = UnionFind(n)
        repeat(1000) {
            val (_, i, j) = heap.poll()
            dsu.union(i, j)
        }

        val counter = buildMap {
            repeat(n) {
                put(dsu.find(it), getOrElse(dsu.find(it)) { 0 } + 1)
            }
        }

        return counter.entries.sortedByDescending { (_, v) -> v }.take(3).fold(1) { acc, (_, v) -> acc * v }
    }

    fun part2(input: List<String>): Long {
        val data = input.map { it.split(",").map(String::toLong) }
        val n = data.size

        val heap = PriorityQueue<Triple<Long, Int, Int>>(compareBy { it.first })
        for (i in 0 until n) {
            val (x1, y1, z1) = data[i]
            for (j in i + 1 until n) {
                val (x2, y2, z2) = data[j]
                val dist = (x1 - x2).square() + (y1 - y2).square() + (z1 - z2).square()
                heap.offer(Triple(dist, i, j))
            }
        }

        val dsu = UnionFind(n)
        while (true) {
            val (_, i, j) = heap.poll()
            dsu.union(i, j)
            if (dsu.sizes[dsu.find(i)] == n) {
                val (x) = data[i]
                val (y) = data[j]

                return x * y
            }
        }
    }

    val testInput = """
        162,817,812
        57,618,57
        906,360,560
        592,479,940
        352,342,300
        466,668,158
        542,29,236
        431,825,988
        739,650,466
        52,470,668
        216,146,977
        819,987,18
        117,168,530
        805,96,715
        346,949,466
        970,615,88
        941,993,340
        862,61,35
        984,92,344
        425,690,689
    """.trimIndent().lines()

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}