package day09

import java.io.File

data class Knot(var x: Int, var y: Int)

val fileList =  File("puzzle_input/day09.txt").readLines()

fun moveHead(head: Knot, direction: Char) = when (direction) {
    'U'  -> Knot(head.x, head.y + 1)
    'L'  -> Knot(head.x - 1, head.y)
    'R'  -> Knot(head.x + 1, head.y)
    'D'  -> Knot(head.x, head.y - 1)
    else -> Knot(0, 0)
  }

fun moveTail(head: Knot, tail: Knot) = when {
  head.x > tail.x + 1 && head.y > tail.y + 1 -> Knot(head.x - 1, head.y - 1)
  head.x < tail.x - 1 && head.y > tail.y + 1 -> Knot(head.x + 1, head.y - 1)
  head.x > tail.x + 1 && head.y < tail.y - 1 -> Knot(head.x - 1, head.y + 1)
  head.x < tail.x - 1 && head.y < tail.y - 1 -> Knot(head.x + 1, head.y + 1)
  head.x > tail.x + 1 -> Knot(head.x - 1, head.y)
  head.x < tail.x - 1 -> Knot(head.x + 1, head.y)
  head.y > tail.y + 1 -> Knot(head.x, head.y - 1)
  head.y < tail.y - 1 -> Knot(head.x, head.y + 1)
  else                -> tail
}

fun a(): Int {
  var visited = mutableSetOf(Knot(0, 0))

  var head = Knot(0, 0)
  var tail = Knot(0, 0)

  for (line in fileList) {
    val direction = line[0]
    val steps = line.slice(2 until line.length).toInt()

    repeat(steps) {
      head = moveHead(head, direction)
      tail = moveTail(head, tail)
      visited += tail
    }
  }

  return visited.size
}

fun b(): Int {
  var visited = mutableSetOf(Knot(0, 0))

  var rope = Array(10) { Knot(0,0) }

  for (line in fileList) {
    val direction = line[0]
    val steps = line.slice(2 until line.length).toInt()

    repeat(steps) {
      rope[0] = moveHead(rope[0], direction)
      for (i in rope.indices.filter { it > 0 }) rope[i] = moveTail(rope[i - 1], rope[i])
      visited += rope[9]
    }
  }

  return visited.size
}