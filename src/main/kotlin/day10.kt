package main.kotlin

import com.microsoft.z3.*
import io.github.rascmatt.z3.Z3Bootstrap

val goalStatePattern = Regex("\\[(.+)]")
val buttonsPattern = Regex("\\((.+)\\)")
val joltagePattern = Regex("\\{(.+)}")

fun day10 (lines: List<String>) {
    check(Z3Bootstrap.init()) { "Failed to initialize Z3 natives" }

    val part1Answer = lines.sumOf { findMinButtonPresses(it) }
    val part2Answer = lines.sumOf { z3Solution(it) }

    println("Day 10 part 1: $part1Answer")
    println("Day 10 part 2: $part2Answer")
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

fun z3Solution(line: String): Int {
    val goalState = joltagePattern.find(line)!!.groupValues.first()
        .replace("{", "")
        .replace("}", "")
        .split(",")
        .map {
            it.toInt()
        }
        .toIntArray()

    val buttons = buttonsPattern.find(line)!!.groupValues.first()
        .replace("(", "")
        .replace(")", "")
        .split(" ")
        .map { outer ->
            outer.split(",").map { it.toInt() }.toIntArray()
        }

    val machine = Machine(goalState, buttons)

    val cfg = hashMapOf("model" to "true")

    Context(cfg).use { context ->
        val opt = context.mkOptimize()

        val x = machine.buttons.indices.map { j ->
            context.mkIntConst("x$j")
        }

        x.forEach { v ->
            opt.Add(context.mkGe(v, context.mkInt(0)))
        }

        for (i in 0 until machine.goalState.size) {
            val terms = mutableListOf<ArithExpr<*>>()
            for (j in machine.buttons.indices) {
                if (machine.buttons[j].contains(i)) {
                    terms.add(x[j] as ArithExpr<*>)
                }
            }
            val sum: ArithExpr<*> = if (terms.isEmpty())
                context.mkInt(0)
            else
                context.mkAdd(*terms.toTypedArray())

            opt.Add(context.mkEq(sum, context.mkInt(machine.goalState[i])))
        }

        val total = context.mkAdd(*x.map { it as ArithExpr<*> }.toTypedArray())
        opt.MkMinimize(total)
        opt.Check()

        val model = opt.model
        return (model.eval(total, true) as IntNum).int
    }
}

data class Day10State (val currentState: List<Boolean>, val availableButtons: List<List<Int>>)

data class Machine (val goalState: IntArray, val buttons: List<IntArray>)