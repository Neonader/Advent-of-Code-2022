package day05

import java.io.File

val fileList = File("puzzle_input/day05.txt").useLines { it.toList() }

var stacks = Array(9) {
  mutableListOf<Char>()
}

fun common() {
// stack parser
  for (line in fileList.slice(7 downTo 0)) {
    var stack = 0
    for (i in line.indices) if (i % 4 == 1) {
      val crate = line[i]
      if (crate != ' ') stacks[stack].add(crate)
      stack++
    }
  }
}

// instruction parser
// we do not speak about this, alright?
val instructions = fileList.slice(10 until fileList.size).map { it.filter { it.isWhitespace() || it.isDigit() }.trim().split("  ").map { it.toInt() } }


fun move9000(instruction: List<Int>) {
  val amount = instruction[0]
  val from = instruction[1] - 1
  val to = instruction[2] - 1

  repeat(amount) {
    stacks[to].add(stacks[from].last())
    stacks[from].removeLast()
  }
}

fun move9001(instruction: List<Int>) {
  val amount = instruction[0]
  val from = instruction[1] - 1
  val to = instruction[2] - 1

  stacks[from].takeLast(amount).forEach { stacks[to].add(it) }

  repeat(amount) { stacks[from].removeLast() }
}

fun a(): String {
  common()

  for (instruction in instructions) {
    move9000(instruction)
  }

  var topCrates = ""

  for (stack in stacks) topCrates += stack.last()

  return topCrates
}

fun b(): String {
  common()

  for (instruction in instructions) {
    move9001(instruction)
  }

  var topCrates = ""

  for (stack in stacks) topCrates += stack.last()

  return topCrates
}