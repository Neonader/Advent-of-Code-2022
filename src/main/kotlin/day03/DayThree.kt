package day03

import java.io.File

val fileList = File("puzzle_input\\day03.txt").useLines { it.toList() }
val priorityLookup = (CharRange('a', 'z') + CharRange('A', 'Z')).toList()

fun a(): Int {
  var sum = 0

  for (line in fileList) {
    val len = line.length

    if (len % 2 == 1) return -1

    val firstCompartment = line.slice(0 until len / 2)
    val secondCompartment = line.slice(len / 2 until len)

    for (item in firstCompartment) {
      if (item in secondCompartment) {
        sum += priorityLookup.indexOf(item) + 1
        break
      }
    }
  }

  return sum
}

fun b(): Int {
  return -1
}