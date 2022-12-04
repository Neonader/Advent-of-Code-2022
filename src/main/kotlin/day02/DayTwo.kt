package day02

import java.io.File

val fileList = File("puzzle_input/day02.txt").useLines { it.toList() }

fun a(): Int {
  var score = 0

  for (line in fileList) {
//  I'm lazy lol
    score += when (line) {
      "A X" -> 1 + 3
      "A Y" -> 2 + 6
      "A Z" -> 3 + 0
      "B X" -> 1 + 0
      "B Y" -> 2 + 3
      "B Z" -> 3 + 6
      "C X" -> 1 + 6
      "C Y" -> 2 + 0
      "C Z" -> 3 + 3
      else  -> return -1
    }
  }

  return score
}

fun b(): Int {
  var score = 0

  for (line in fileList) {
//  I'm still lazy
    score += when (line) {
      "A X" -> 3 + 0
      "A Y" -> 1 + 3
      "A Z" -> 2 + 6
      "B X" -> 1 + 0
      "B Y" -> 2 + 3
      "B Z" -> 3 + 6
      "C X" -> 2 + 0
      "C Y" -> 3 + 3
      "C Z" -> 1 + 6
      else  -> return -1
    }
  }

  return score
}