package day3

import ext.splitInTwo
import ext.intersect

val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
val PRIORITIES = ALPHABET + ALPHABET.uppercase()

fun parseRucksacks(input: String): Rucksacks {
    return input
        .lines()
        .map { line -> line.splitInTwo().let { Rucksack(it.first.toList(), it.second.toList()) } }
}

typealias Rucksacks = List<Rucksack>

fun Rucksacks.sumOfPrioritiesOfItemsInBothCompartments(): Int {
    return this
        .map { it.getItemInBothCompartments() }
        .filterNotNull()
        .map(::getPriorityOfItem)
        .sum()
}

fun Rucksacks.sumOfBadgePriorities(): Int {
    return this
        .chunked(3)
        .map { rucksackGroup -> rucksackGroup.map(Rucksack::getItems) }
        .map { rucksackGroupItems -> rucksackGroupItems.map(Items::toSet).toSet().intersect().first() }
        .map (::getPriorityOfItem)
        .sum()
}

data class Rucksack(val leftCompartment: Items, val rightCompartment: Items) {
    fun getItems(): Items = leftCompartment + rightCompartment

    fun getItemInBothCompartments(): Char? {
        return leftCompartment.toSet().intersect(rightCompartment.toSet()).toList().firstOrNull()
    }
}
typealias Items = List<Char>

fun getPriorityOfItem(char: Char): Int {
    return PRIORITIES.indexOf(char) + 1
}
