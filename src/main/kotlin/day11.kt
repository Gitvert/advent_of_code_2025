package main.kotlin

fun day11 (lines: List<String>) {
    val devices: Map<String, MutableSet<String>> = lines.associate { line ->
        val key = line.split(": ")[0]
        val values = line.split(": ")[1].split(" ").toMutableSet()

        Pair(key, values)
    }

    val start = devices["you"]!!
    var paths = mutableListOf<MutableSet<String>>()
    var routeCount = 0

    start.forEach {
        paths.add(mutableSetOf(it))
    }

    while(paths.isNotEmpty()) {
        val newPaths = mutableListOf<MutableSet<String>>()

        paths.forEach { path ->
            devices[path.last()]!!.forEach { neighbor ->
                if (neighbor == "out") {
                    routeCount++
                }
                else if (!path.contains(neighbor) && neighbor != "you") {
                    val extendedPath = path.toMutableSet()
                    extendedPath.add(neighbor)
                    newPaths.add(extendedPath)
                }
            }
        }

        paths = newPaths
    }

    println("Day 11 part 1: $routeCount")
}