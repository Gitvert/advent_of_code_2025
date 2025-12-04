package main.kotlin

data class Position(val x: Long, val y: Long)

val allDirections = listOf(
    -1 to -1, 0 to -1, 1 to -1,
    -1 to  0,          1 to  0,
    -1 to  1, 0 to  1, 1 to  1
).map { pair -> Position(pair.first.toLong(), pair.second.toLong()) }
