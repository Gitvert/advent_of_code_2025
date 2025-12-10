package main.kotlin

fun day9 (lines: List<String>) {
    val corners = lines
        .map { Position (it.split(',')[0].toLong(), it.split(',')[1].toLong()) }
    var maxArea = 0L

    corners.forEach { outer ->
        corners.forEach { inner ->
            val area = rectangleArea(inner, outer)
            if (area > maxArea) {
                maxArea = area
            }
        }
    }

    println("Day 9 part 1: $maxArea")
}

fun rectangleArea(a: Position, b: Position): Long =
    (kotlin.math.abs(a.x - b.x) + 1) * (kotlin.math.abs(a.y - b.y) + 1)

