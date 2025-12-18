package main.kotlin

val figSizes = listOf(7, 7, 6, 7, 5, 7)

fun day12 (lines: List<String>) {
    val answer = lines.sumOf { line ->
        val sizeString = line.split(":").first()
        val height = sizeString.split("x").first().toLong()
        val width = sizeString.split("x")[1].toLong()

        val spaceAvailable = height * width

        val counts = line.split(": ")[1].split(" ").map { it.toLong() }

        var totalSize = 0L

        counts.forEachIndexed { i, count ->
            totalSize += count * figSizes[i]
        }

        if (totalSize < spaceAvailable) {
            1
        } else {
            0
        }
    }

    println("Day 12 part 1: $answer")
}