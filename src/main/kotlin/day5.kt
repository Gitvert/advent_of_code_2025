package main.kotlin

fun day5 (lines: List<String>) {
    day5part1()
    day5part2()
}

fun day5part1() {
    var freshIds = 0

    day5Ids.forEach { id ->
        if (day5Ranges.any { id >= it.first && id <= it.second }) {
            freshIds++
        }
    }

    println("Day 5 part 1: $freshIds")
}

fun day5part2() {
    var ranges = day5Ranges

    repeat (5) {
        ranges = mergeRanges(ranges)
        ranges = removeSwallowedRanges(ranges)
    }

    var freshIds = 0L

    ranges.forEach { freshIds += (it.second - it.first + 1) }

    println("Day 5 part 2: $freshIds")
}

fun mergeRanges(ranges: Set<Pair<Long, Long>>): Set<Pair<Long, Long>> {
    val newRanges = mutableSetOf<Pair<Long, Long>>()

    ranges.forEachIndexed { i, outer ->
        var hasOverlap = false
        ranges.forEachIndexed { j, inner ->
            if (j != i) {
                if (outer.first >= inner.first && outer.first <= inner.second) {
                    newRanges.add(
                        Pair(Math.min(inner.first, outer.first), Math.max(inner.second, outer.second))
                    )
                    hasOverlap = true
                }
            }
        }
        if (!hasOverlap) {
            newRanges.add(Pair(outer.first, outer.second))
        }
    }

    return newRanges
}

fun removeSwallowedRanges(ranges: Set<Pair<Long, Long>>): Set<Pair<Long, Long>> {
    val newRanges = mutableSetOf<Pair<Long, Long>>()

    ranges.forEachIndexed { i, outer ->
        var isSwallowed = false
        ranges.forEachIndexed { j, inner ->
            if (j != i) {
                if (outer.first >= inner.first && outer.second <= inner.second) {
                    isSwallowed = true
                }
            }
        }

        if (!isSwallowed) {
            newRanges.add(Pair(outer.first, outer.second))
        }
    }

    return newRanges
}