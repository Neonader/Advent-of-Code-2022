package day01

import java.io.File

val fileList = File("puzzle_input\\day01.txt").useLines { it.toList() }

fun a(): Int {
  var mostCalories = 0
  var calories = 0

  for (line in fileList) {
    if (line == "") {
      calories = 0
    } else {
      calories += line.toInt()
    }

    if (calories > mostCalories) mostCalories = calories
  }

  return mostCalories
}

fun b(): Int {
  var mostCalories = mutableListOf(0, 0, 0)
  var calories = 0

  for (line in fileList) {
    if (line == "") {
      calories = 0
    } else {
      calories += line.toInt()
    }

    if (calories > mostCalories[0]) {
      mostCalories[0] = calories
      mostCalories.sort()
    }
  }

  return mostCalories.sum()
}

