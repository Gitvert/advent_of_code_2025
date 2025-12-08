package main.kotlin

fun day7 (lines: List<String>) {
    var beams = mutableSetOf<Position>()
    val splitters = mutableSetOf<Position>()
    val lastY = lines.size.toLong()
    var splits = 0

    lines[0].forEachIndexed { i, cell ->
        if (cell == 'S') {
            beams.add(Position(i.toLong(), 0))
        }
    }

    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            if (cell == 'S') {
                beams.add(Position(x.toLong(), y.toLong()))
            } else if (cell == '^') {
                splitters.add(Position(x.toLong(), y.toLong()))
            }
        }
    }

    while (true) {
        val newBeams = mutableSetOf<Position>()
        beams.forEach { beam ->
            if (splitters.contains(Position(beam.x, beam.y + 1))) {
                newBeams.add(Position(beam.x + 1, beam.y + 1))
                newBeams.add(Position(beam.x - 1, beam.y + 1))
                splits++
            } else {
                newBeams.add(Position(beam.x, beam.y + 1))
            }
        }
        beams = newBeams

        if (beams.first().y == lastY) {
            break
        }
    }

    println("Day 7 part 1: $splits")
}