package main.kotlin

fun day2 (lines: List<String>) {
    day2part1(lines[0])
    day2part2(lines[0])
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

    var part2Answer = 0L

    ranges.forEach { range ->
        val start = (range.split('-')[0]).toLong(10)
        val end = (range.split('-')[1]).toLong(10)

        for (i in start..end) {
            val id = i.toString(10)

            if (id.length == 2 && id[0] == id[1]) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 3 && id[0] == id[1] && id[1] == id[2]) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 4 && (
                (id[0] == id[1] && id[1] == id[2] && id[2] == id[3])
                || (id[0] == id[2] && id[1] == id[3])
            )) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 5 && id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4]) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 6 && (
                (id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4] && id[4] == id[5])
                || (id[0] == id[3] && id[1] == id[4] && id[2] == id[5])
                || (id[0] == id[2] && id[2] == id[4] && id[1] == id[3] && id[3] == id[5])
            )) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 7 && id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4] && id[4] == id[5]
                && id[5] == id[6]) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 8 && (
                (id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4] && id[4] == id[5] && id[5] == id[6] && id[6] == id[7])
                    || (id[0] == id[4] && id[1] == id[5] && id[2] == id[6] && id[3] == id[7])
                    || (id[0] == id[2] && id[2] == id[4] && id[4] == id[6] && id[1] == id[3] && id[3] == id[5] && id[5] == id[7])
            )) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 9 && ((id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4] && id[4] == id[5]
                && id[5] == id[6] && id[6] == id[7] && id[7] == id[8]) || (
                    id[0] == id[3] && id[3] == id[6]
                    && id[1] == id[4] && id[4] == id[7]
                    && id[2] == id[5] && id[5] == id[8]
                ))) {
                part2Answer += (id).toLong(10)
                continue
            }

            if (id.length == 10 && (
                (id[0] == id[1] && id[1] == id[2] && id[2] == id[3] && id[3] == id[4] && id[4] == id[5] && id[5] == id[6] && id[6] == id[7] && id[7] == id[8] && id[8] == id[9])
                    || (id[0] == id[5] && id[1] == id[6] && id[2] == id[7] && id[3] == id[8] && id[4] == id[9])
                    || (id[0] == id[2] && id[2] == id[4] && id[4] == id[6] && id[6] == id[8] && id[1] == id[3] && id[3] == id[5] && id[5] == id[7] && id[7] == id[9])
            )) {
                part2Answer += (id).toLong(10)
                continue
            }
        }
    }

    println("Day 2 part 2: $part2Answer")
}
