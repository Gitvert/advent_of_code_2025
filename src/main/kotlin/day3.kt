package main.kotlin

fun day3 (lines: List<String>) {
    day3part1(lines)
    day3part2(lines)
}

fun day3part1(lines: List<String>) {
    var totalJoltage = 0L

    lines.forEach { line ->
        var largestNumber = -1
        var secondLargestNumber = -1
        var largestNumberPos = -1
        for (i in 0 until line.length - 1) {
            if (Integer.parseInt(line[i].toString(), 10) > largestNumber) {
                largestNumber = Integer.parseInt(line[i].toString(), 10)
                largestNumberPos = i
            }
        }

        for (i in largestNumberPos + 1 until line.length) {
            if (Integer.parseInt(line[i].toString(), 10) > secondLargestNumber) {
                secondLargestNumber = Integer.parseInt(line[i].toString(), 10)
            }
        }

        totalJoltage += (largestNumber.toString() + secondLargestNumber.toString()).toLong(10)
    }

    println("Day 3 part 1: $totalJoltage")
}

fun day3part2(lines: List<String>) {
    var totalJoltage = 0L

    lines.forEach { line ->
        var startIndex = 0
        var endIndex = line.length - 11
        var largestJoltage = ""

        repeat(12) {
            val result = findLargestNumber(line, startIndex, endIndex)

            largestJoltage += result.number.toString()
            startIndex = result.position + 1
            endIndex++
        }

        totalJoltage += largestJoltage.toLong()
    }

    println("Day 3 part 2: $totalJoltage")
}

fun findLargestNumber(line: String, startIndex: Int, endIndex: Int): JoltageHelper {
    var largestNumber = -1
    var largestNumberPos = -1
    for (i in startIndex until endIndex) {
        if (Integer.parseInt(line[i].toString(), 10) > largestNumber) {
            largestNumber = Integer.parseInt(line[i].toString(), 10)
            largestNumberPos = i
        }
    }

    return JoltageHelper(largestNumber, largestNumberPos)
}

data class JoltageHelper(val number: Int, val position: Int)