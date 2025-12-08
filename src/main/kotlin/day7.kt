package main.kotlin

fun day7 (lines: List<String>) {
    day7part1(lines)
    day7part2(lines)
}

fun day7part1 (lines: List<String>) {
    var beams = mutableSetOf<Position>()
    val splitters = mutableSetOf<Position>()
    val lastY = lines.size.toLong()
    var splits = 0

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

fun day7part2 (lines: List<String>) {
    var timelines = mutableMapOf<Position, Long>()
    val splitters = mutableSetOf<Position>()
    val lastY = lines.size.toLong()

    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            if (cell == 'S') {
                timelines[(Position(x.toLong(), y.toLong()))] = 1L
            } else if (cell == '^') {
                splitters.add(Position(x.toLong(), y.toLong()))
            }
        }
    }

    while (true) {
        val newTimelines = mutableMapOf<Position, Long>()

        timelines.forEach { timeline ->
            if (splitters.contains(Position(timeline.key.x, timeline.key.y + 1))) {
                newTimelines.merge(Position(timeline.key.x + 1, timeline.key.y + 1), timeline.value) { old, new ->
                    old + new
                }
                newTimelines.merge(Position(timeline.key.x - 1, timeline.key.y + 1), timeline.value) { old, new ->
                    old + new
                }

            } else {
                newTimelines.merge(Position(timeline.key.x, timeline.key.y + 1), timeline.value) { old, new ->
                    old + new
                }
            }
        }

        timelines = newTimelines

        if (timelines.keys.first().y == lastY) {
            break
        }
    }

    val answer = timelines.values.sum()

    println("Day 7 part 2: $answer")
}
