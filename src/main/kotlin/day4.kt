package main.kotlin

fun day4 (lines: List<String>) {
    val paperRolls = mutableListOf<Position>()

    lines.forEachIndexed { y, row ->
        row.forEachIndexed { x, col ->
            if (col == '@') {
                paperRolls.add(Position(x.toLong(), y.toLong()))
            }
        }
    }

    val initialSize = paperRolls.size
    val answerPart1 = paperRolls.filter { countNeighbors(it, paperRolls) < 4 }.size

    println("Day 4 part 1: $answerPart1")

    do {
        val changed = paperRolls.removeIf { countNeighbors(it, paperRolls) < 4 }
    } while (changed)

    val answerPart2 = initialSize - paperRolls.size

    println("Day 4 part 2: $answerPart2")
}

fun countNeighbors(position: Position, paperRolls: List<Position>): Int {
    var neighbors = 0

    allDirections.forEach { direction ->
        if (paperRolls.contains(Position(position.x + direction.x, position.y + direction.y))) {
            neighbors++
        }
    }

    return neighbors
}