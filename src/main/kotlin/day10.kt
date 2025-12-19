package main.kotlin

//import com.microsoft.z3.*

val goalStatePattern = Regex("\\[(.+)]")
val buttonsPattern = Regex("\\((.+)\\)")

fun day10 (lines: List<String>) {
    val part1Answer = lines.sumOf { findMinButtonPresses(it) }

println("Day 10 part 1: $part1Answer")
}

fun findMinButtonPresses(line: String): Int {
    val goalState = goalStatePattern.find(line)!!.groupValues.first()
        .filter { it == '.' || it == '#' }
        .map {
            it != '.'
        }
    val availableButtons = buttonsPattern.find(line)!!.groupValues.first()
        .replace("(", "")
        .replace(")", "")
        .split(" ")
        .map { outer ->
            outer.split(",").map { it.toInt() }
        }

    var states = mutableListOf(Day10State(goalState.map { false }, availableButtons))
    var presses = 0

    while (true) {
        val newStates = mutableListOf<Day10State>()
        presses++

        states.forEach { state ->
            state.availableButtons.forEachIndexed { i, buttons ->
                val currentState = state.currentState.toMutableList()
                buttons.forEach { button ->
                    currentState[button] = !currentState[button]
                }

                if (currentState == goalState) {
                    return presses
                }

                val newAvailableButtons = availableButtons.filterIndexed { index, _ ->
                    index != i
                }

                newStates.add(Day10State(currentState.toList(), newAvailableButtons))
            }
        }

        states = newStates
    }
}

data class Day10State (val currentState: List<Boolean>, val availableButtons: List<List<Int>>)