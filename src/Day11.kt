fun main() {
    fun part1(input: List<String>): Int {
        val graph = buildMap {
            input.forEach { line ->
                val (currentNode, links) = line.split(": ")

                put(currentNode, links.split(" "))
            }
        }

        var ret = 0

        val queue = ArrayDeque<String>()
        queue.add("you")

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val links = graph[current]!!

            links.forEach {
                if (it == "out") {
                    ret++
                } else {
                    queue.addLast(it)
                }
            }
        }

        return ret
    }

    fun part2(input: List<String>): Long {
        val graph = buildMap {
            input.forEach { line ->
                val (currentNode, links) = line.split(": ")

                put(currentNode, links.split(" "))
            }
        }

        // val queue = ArrayDeque<Triple<String, Boolean, Boolean>>()
        // queue.add(Triple("svr", false, false))

        val cache = mutableMapOf<Triple<String, Boolean, Boolean>, Long>()

        fun seen(current: String, visitedDac: Boolean = false, visitedFft: Boolean = false): Long {
            if (Triple(current, visitedDac, visitedFft) in cache) {
                return cache[Triple(current, visitedDac, visitedFft)]!!
            }

            return (if (current == "out") {
                if (visitedDac && visitedFft) 1L else 0L
            } else {
                graph[current]!!.sumOf {
                    seen(it, visitedDac || it == "dac", visitedFft || it == "fft")
                }
            }).also {
                cache[Triple(current, visitedDac, visitedFft)] = it
            }
        }

        // while (queue.isNotEmpty()) {
        //     val (current, visitedDac, visitedFft) = queue.removeFirst()
        //     println("testing $current ${queue.size}")
        //     val links = graph[current]!!
        //
        //     links.forEach {
        //         if (it == "out") {
        //             if (visitedDac && visitedFft) {
        //                 ret++
        //             }
        //         } else {
        //             queue.addLast(Triple(it, visitedDac || it == "dac", visitedFft || it == "fft"))
        //         }
        //     }
        // }

        return seen("svr")
    }

    // val testInput = """
    //     aaa: you hhh
    //     you: bbb ccc
    //     bbb: ddd eee
    //     ccc: ddd eee fff
    //     ddd: ggg
    //     eee: out
    //     fff: out
    //     ggg: out
    //     hhh: ccc fff iii
    //     iii: out
    // """.trimIndent().lines()

    val testInput = """
        svr: aaa bbb
        aaa: fft
        fft: ccc
        bbb: tty
        tty: ccc
        ccc: ddd eee
        ddd: hub
        hub: fff
        eee: dac
        dac: fff
        fff: ggg hhh
        ggg: out
        hhh: out
    """.trimIndent().lines()

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}