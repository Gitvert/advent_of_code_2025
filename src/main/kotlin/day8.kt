package main.kotlin

import kotlin.math.sqrt

fun day8 (lines: List<String>) {
    val junctionBoxes = mutableListOf<Point>()
    val distances = mutableListOf<JunctionDistance>()

    lines.forEach {
        val xyz = it.split(',')
        junctionBoxes.add(Point(xyz[0].toDouble(), xyz[1].toDouble(), xyz[2].toDouble()))
    }

    junctionBoxes.forEachIndexed { i, outer ->
        junctionBoxes.forEachIndexed { j, inner ->
            if (j > i) {
                distances.add(JunctionDistance(
                    Pair(inner, outer),
                    euclideanDistance(inner, outer)
                ))
            }
        }
    }

    val sortedDistances = distances.sortedBy { it.distance }

    var circuits = mutableSetOf<Circuit>()

    sortedDistances.forEachIndexed { i, distance ->
        var foundExistingCircuit = false
        circuits.forEach { circuit ->
            if (circuit.junctionBoxes.contains(distance.points.first) || circuit.junctionBoxes.contains(distance.points.second)) {
                circuit.junctionBoxes.add(distance.points.first)
                circuit.junctionBoxes.add(distance.points.second)

                foundExistingCircuit = true
            }
        }

        if (!foundExistingCircuit) {
            circuits.add(Circuit(mutableSetOf(distance.points.first, distance.points.second)))
        }

        circuits = mergeCircuits(circuits)

        if (i == 999) {
            val answer = circuits
                .sortedByDescending { it.junctionBoxes.size }
                .take(3)
                .map { it.junctionBoxes.size }
                .reduce { acc, n -> acc * n }
            println("Day 8 part 1: $answer")

            return
        }
    }


}

fun mergeCircuits(circuits: Set<Circuit>): MutableSet<Circuit> {
    val mergedCircuits = mutableSetOf<Circuit>()

    circuits.forEachIndexed { i, outer ->
        var anyMatch = false
        circuits.forEachIndexed { j, inner ->
            if (i != j) {
                if (inner.junctionBoxes.intersect(outer.junctionBoxes).isNotEmpty()) {
                    mergedCircuits.add(Circuit((inner.junctionBoxes + outer.junctionBoxes).toMutableSet()))
                    anyMatch = true
                }
            }
        }
        if (!anyMatch) {
            mergedCircuits.add(Circuit(outer.junctionBoxes))
        }
    }

    return mergedCircuits
}

fun euclideanDistance(a: Point, b: Point): Double {
    return sqrt(
        (a.x - b.x) * (a.x - b.x) +
            (a.y - b.y) * (a.y - b.y) +
            (a.z - b.z) * (a.z - b.z)
    )
}

data class JunctionDistance (val points: Pair<Point, Point>, val distance: Double)

data class Circuit (val junctionBoxes: MutableSet<Point>)