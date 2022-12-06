package day06

import java.io.File

val fileString = File("puzzle_input/day06.txt").useLines { it.first() }

fun a(): Int {
  for (i in fileString.indices) if (i > 2) {
    if (setOf(
          fileString[i],
          fileString[i - 1],
          fileString[i - 2],
          fileString[i - 3]
        ).size == 4
    ) {
      return i + 1
    }
  }

  return -1
}

fun b(): Int {
  for (i in fileString.indices) if (i > 12) {
//  you can keep a secret, right?
    if (setOf(
        fileString[i],
        fileString[i - 1],
        fileString[i - 2],
        fileString[i - 3],
        fileString[i - 4],
        fileString[i - 5],
        fileString[i - 6],
        fileString[i - 7],
        fileString[i - 8],
        fileString[i - 9],
        fileString[i - 10],
        fileString[i - 11],
        fileString[i - 12],
        fileString[i - 13],
      ).size == 14
    ) {
      return i + 1
    }
  }

  return -1
}