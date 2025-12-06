package main.kotlin

import java.util.regex.Pattern

fun day6 (lines: List<String>) {
    day6part1(lines)
    day6part2(lines)
}

fun day6part1(lines: List<String>) {
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

fun day6part2(lines: List<String>) {
    val line1 = lines[0]
    val line2 = lines[1]
    val line3 = lines[2]
    val line4 = lines[3]

    val operators = lines[4]

    var listOfNumbers = mutableListOf<List<Char>>()
    var sum = 0L

    for (i in line1.lastIndex downTo 0) {
        listOfNumbers.add(listOf(line1[i], line2[i], line3[i], line4[i]))

        if (operators[i] != ' ') {
            sum += performMath(listOfNumbers, operators[i])
            listOfNumbers = mutableListOf()
        }
    }

    println("Day 6 part 2: $sum")
}

fun performMath(listOfNumbers: MutableList<List<Char>>, operator: Char): Long {
    val numberList = mutableListOf<String>()

    listOfNumbers
        .filterNot { numbers -> numbers.all { it == ' ' } }
        .forEach { numbers ->
            var stringNumber = ""
            numbers
                .filterNot { it == ' ' }
                .forEach { stringNumber += it}
            numberList.add(stringNumber)
        }

    var result = if (operator == '+') { 0L } else { 1L }

    numberList.map { it.toLong() }.forEach {
        if (operator == '+') {
            result += it
        } else {
            result *= it
        }
    }

    return result
}