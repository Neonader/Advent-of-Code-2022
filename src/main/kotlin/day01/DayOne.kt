package day01

import java.io.File

fun a() {
  val fileList = File("puzzle_input\\day01.txt").useLines { it.toList() }

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

  println(mostCalories)
}

fun b() {
  val fileList = File("puzzle_input\\day01.txt").useLines { it.toList() }

  var mostCalories = mutableListOf<Int>(0, 0, 0)
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

  println(mostCalories.sum())
}

