package main.kotlin

import java.util.regex.Pattern

fun day6 (lines: List<String>) {
    val pattern: Pattern = Pattern.compile("\\s")
    val line1 = lines[0].split(pattern).filter { it.isNotEmpty() }
    val line2 = lines[1].split(pattern).filter { it.isNotEmpty() }
    val line3 = lines[2].split(pattern).filter { it.isNotEmpty() }
    val line4 = lines[3].split(pattern).filter { it.isNotEmpty() }

    val operators = lines[4].split(pattern).filter { it.isNotEmpty() }

    var sum = 0L

    for (i in 0 until line1.size) {
        if (operators[i] == "+") {
            sum += (line1[i].toLong() + line2[i].toLong() + line3[i].toLong() + line4[i].toLong())
        } else {
            sum += (line1[i].toLong() * line2[i].toLong() * line3[i].toLong() * line4[i].toLong())
        }
    }

    println("Day 6 part 1: $sum")
}