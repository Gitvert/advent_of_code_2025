package main.kotlin

fun day1 (lines: List<String>) {
    var dialLocation = 50
    var noOfZerosPart1 = 0
    var noOfZerosPart2 = 0

    lines.forEach {
        val dir = it[0]
        val steps = Integer.parseInt(it.subSequence(1, it.length).toString(), 10)

        for (i in 0 until steps) {
            if (dir == 'R') {
                dialLocation++
                if (dialLocation > 99) {
                    dialLocation = 0
                }
            } else {
                dialLocation--
                if (dialLocation < 0) {
                    dialLocation = 99
                }
            }

            if (dialLocation == 0) {
                noOfZerosPart2++
            }

        }

        if (dialLocation == 0) {
            noOfZerosPart1++
        }
    }

    println("Day 1 part 1: $noOfZerosPart1")
    println("Day 1 part 2: $noOfZerosPart2")
}