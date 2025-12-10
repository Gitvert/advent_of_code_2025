package main.kotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day8Test {

    @Test
    fun should_merge_overlapping_sets() {
        val size = mergeCircuits(setOf(
            Circuit(mutableSetOf(Point(1.0, 2.0, 3.0), Point(4.0, 5.0, 6.0))),
            Circuit(mutableSetOf(Point(1.0, 2.0, 3.0), Point(7.0, 8.0, 9.0)))
        )).size

        assertEquals(1, size)
    }
}