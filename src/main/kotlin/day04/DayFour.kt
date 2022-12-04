package day04

import java.io.File

val fileList = File("puzzle_input/day04.txt").useLines { it.toList() }

fun a(): Int {
  var sum = 0

  for (line in fileList) {
    val sections = line.split(',', '-').map { it.toInt() }

    if (sections.size != 4) return -1

    if (
      sections[0] <= sections[2] && sections[1] >= sections[3] ||
      sections[2] <= sections[0] && sections[3] >= sections[1]
    ) sum++
  }

  return sum
}

fun b(): Int {
  var sum = 0

  for (line in fileList) {
    val sections = line.split(',', '-').map { it.toInt() }

    if (sections.size != 4) return -1

    if (
      sections[1] >= sections[2] && sections[0] <= sections[3] ||
      sections[3] >= sections[0] && sections[2] <= sections[1]
    ) sum++
  }

  return sum
}