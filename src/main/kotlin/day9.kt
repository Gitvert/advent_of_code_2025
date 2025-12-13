package main.kotlin

val cache = mutableMapOf<Position, Boolean>()

fun day9 (lines: List<String>) {
    val corners = lines
        .map { Position (it.split(',')[0].toLong(), it.split(',')[1].toLong()) }

    day9part1(corners)
    day9part2(corners)
}

fun day9part1(corners: List<Position>) {
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

fun day9part2(corners: List<Position>) {
    val edges = mutableListOf<Pair<Position, Position>>()
    for (pair in corners.zipWithNext()) {
        edges.add(pair)
    }
    edges.add(Pair(corners.last(), corners.first()))

    var maxArea = 0L

    corners.forEachIndexed { i, outer ->
        corners.forEachIndexed { j, inner ->
            if (j > i) {
                val area = rectangleArea(inner, outer)
                if (area > maxArea) {
                    val allEdges = rectanglePerimeter(inner, outer)

                    if (allEdges.all { cachedBoundCheck(edges, it) }) {
                        maxArea = area
                    }
                }
            }
        }
    }

    println("Day 9 part 2: $maxArea")
}

fun rectangleArea(a: Position, b: Position): Long =
    (kotlin.math.abs(a.x - b.x) + 1) * (kotlin.math.abs(a.y - b.y) + 1)

fun rectanglePerimeter(c1: Position, c2: Position): Set<Position> {
    val minX = minOf(c1.x, c2.x)
    val maxX = maxOf(c1.x, c2.x)
    val minY = minOf(c1.y, c2.y)
    val maxY = maxOf(c1.y, c2.y)

    val result = mutableSetOf<Position>()

    for (x in minX..maxX) {
        result += Position(x, minY)
        result += Position(x, maxY)
    }

    for (y in minY..maxY) {
        result += Position(minX, y)
        result += Position(maxX, y)
    }

    return result
}

fun cachedBoundCheck(edges: List<Pair<Position, Position>>, point: Position): Boolean {
    return cache.getOrPut(point) {
        pointBoundedByEdges(edges, point)
    }
}

fun pointBoundedByEdges(edges: List<Pair<Position, Position>>, point: Position): Boolean {
    var hasBelow = false
    var hasAbove = false
    var hasLeft  = false
    var hasRight = false

    for ((p1, p2) in edges) {
        if (p1.y == p2.y) {
            val minX = minOf(p1.x, p2.x)
            val maxX = maxOf(p1.x, p2.x)

            if (point.x in minX..maxX) {
                if (point.y <= p1.y) hasBelow = true
                if (point.y >= p1.y) hasAbove = true
            }
        }

        if (p1.x == p2.x) {
            val minY = minOf(p1.y, p2.y)
            val maxY = maxOf(p1.y, p2.y)

            if (point.y in minY..maxY) {
                if (point.x <= p1.x) hasLeft = true
                if (point.x >= p1.x) hasRight = true
            }
        }

        if (hasBelow && hasAbove && hasLeft && hasRight) {
            return true
        }
    }

    return hasBelow && hasAbove && hasLeft && hasRight
}