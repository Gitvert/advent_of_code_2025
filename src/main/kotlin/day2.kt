package main.kotlin

fun day2 (lines: List<String>) {
    day2part1(lines[0])
}

fun day2part1(input: String) {
    val ranges = input.split(',')

    var part1Answer = 0L

    ranges.forEach { range ->
        val start = (range.split('-')[0]).toLong(10)
        val end = (range.split('-')[1]).toLong(10)

        for (i in start..end) {
            val id = i.toString(10)

            if (id.length % 2 != 0) {
                continue
            }

            var invalidId = true

            for (j in 0 until (id.length / 2)) {
                if (id[j] != id[j + (id.length / 2)]) {
                    invalidId = false
                }
            }

            if (invalidId) {
                part1Answer += (id).toLong(10)
            }
        }
    }

    println("Day 2 part 1: $part1Answer")
}

fun day2part2(input: String) {
    val ranges = input.split(',')
}
