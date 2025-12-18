package main.kotlin

val deviceCache = hashMapOf<DeviceCacheKey, Long>()

fun day11 (lines: List<String>) {
    day11Part1(lines)
    day11Part2(lines)
}

fun day11Part1(lines: List<String>) {
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
                else if (!path.contains(neighbor)) {
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

fun day11Part2(lines: List<String>) {
    val devices: Map<String, MutableSet<String>> = lines.associate { line ->
        val key = line.split(": ")[0]
        val values = line.split(": ")[1].split(" ").toMutableSet()

        Pair(key, values)
    }

    val routeCount = findPathsToOut("svr", false, false, devices)

    println("Day 11 part 2: $routeCount")
}

fun findPathsToOut(device: String, visitsDac: Boolean, visitsFft: Boolean, devices: Map<String, MutableSet<String>>): Long {
    if (device == "out") {
        if (visitsFft && visitsDac) {
            return 1
        } else {
            return 0
        }
    }

    val cacheKey = DeviceCacheKey(device, visitsDac, visitsFft)

    if (deviceCache.contains(cacheKey)) {
        return deviceCache[cacheKey]!!
    }

    var pathsOut = 0L

    devices[device]!!.forEach {
        pathsOut += findPathsToOut(
            it,
            visitsDac || device == "dac",
            visitsFft || device == "fft",
            devices
        )
    }

    deviceCache[cacheKey] = pathsOut

    return pathsOut
}

data class DeviceCacheKey(val device: String, val visitsDac: Boolean, val visitsFft: Boolean)