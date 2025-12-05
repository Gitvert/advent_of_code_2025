package main.kotlin

fun day5 (lines: List<String>) {
    var freshIds = 0

    day5Ids.forEach { id ->
        if (day5Ranges.any { id >= it.first && id <= it.second }) {
            freshIds++
        }
    }

    println("Day 5 part 1: $freshIds")
}