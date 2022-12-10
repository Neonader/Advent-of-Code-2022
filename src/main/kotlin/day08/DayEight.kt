package day08

import java.io.File

val fileList =  File("puzzle_input/day08.txt").useLines { it.toList() }
val forest = fileList.map { row -> row.toList().map { col -> col.code } }

fun visible(row: Int, col: Int): Boolean {
  val tree = forest[row][col]

  if (
    row == 0 || row == 98 ||
    col == 0 || col == 98
  ) return true

  var visible = true

// visible from above
  for (otherTree in forest.slice(0 until row))
    visible = visible && otherTree[col] < tree
  if (visible) return true

// visible from left
  visible = true
  for (otherTree in forest[row].slice(0 until col))
    visible = visible && otherTree < tree
  if (visible) return true

// visible from right
  visible = true
  for (otherTree in forest[row].slice(col + 1 until forest[row].size))
    visible = visible && otherTree < tree
  if (visible) return true

// visible from below
  visible = true
  for (otherTree in forest.slice(row + 1 until forest[col].size))
    visible = visible && otherTree[col] < tree
  return visible
}

fun scenicScore(row: Int, col: Int): Int {
  val tree = forest[row][col]
  var score = -1

  return score
}

fun a(): Int {
  var sum = 0

  for (row in 0..98) for (col in 0..98)
    if (visible(row, col)) sum++

  return sum
}

fun b(): Int {
  var max = 0

  for (row in 0..98) for (col in 0..98)
    if (scenicScore(row, col) > max)
      max = scenicScore(row, col)

  return max
}