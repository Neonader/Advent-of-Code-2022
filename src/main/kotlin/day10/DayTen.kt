package day10

import java.io.File

val fileList =  File("puzzle_input/day10.txt").readLines()
var registerX = 1

fun a(): Int {
  val strengths = mutableListOf<Int>()

  var cycle = 1

  for (line in fileList) {
    if ((cycle + 20) % 40 == 0) strengths.add(cycle * registerX)
    cycle++

    if (line == "noop") { continue }

    if ((cycle + 20) % 40 == 0) strengths.add(cycle * registerX)
    cycle++
    registerX += line.slice(5 until line.length).toInt()

    if (cycle > 220) break
  }

  return strengths.sum()
}

fun b(): String {
  var output = "\n"

  var cycle = 0

  fun newCycle() {
    cycle++

    output += if (
      cycle % 40     == registerX ||
      cycle % 40 - 1 == registerX ||
      cycle % 40 - 2 == registerX
    ) '#'
    else '.'

    if (cycle % 40 == 0) output += '\n'
    println("$cycle: $registerX")
  }

  for (line in fileList) {
    if (line == "noop") {
      newCycle()
    } else {
      newCycle()
      newCycle()
      registerX += line.slice(5 until line.length).toInt()
    }
  }

  return output
}