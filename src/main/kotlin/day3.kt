package main.kotlin

fun day3 (lines: List<String>) {
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