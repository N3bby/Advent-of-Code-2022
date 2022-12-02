package util

import java.io.BufferedReader
import java.io.FileReader
import java.util.stream.Collectors.toList

fun readLines(path: String): List<String> {
    return BufferedReader(FileReader(path)).lines().collect(toList())
}

fun readFile(path: String): String {
    return BufferedReader(FileReader(path)).readText()
}

fun readInput(day: Int): String {
    return readFile("input/day$day.txt").trim()
}
