package day08

import java.io.File

val fileList = File("puzzle_input/day08.txt").useLines { it.toList() }
val forest = fileList.map { row -> row.toList().map { col -> col.code } }
val width = forest[0].size
val height = forest.size

fun visible(row: Int, col: Int): Boolean {
  val tree = forest[row][col]

  if (
    row == 0 || row == height ||
    col == 0 || col == width
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
  for (otherTree in forest[row].slice(col + 1 until width))
    visible = visible && otherTree < tree
  if (visible) return true

// visible from below
  visible = true
  for (otherTree in forest.slice(row + 1 until height))
    visible = visible && otherTree[col] < tree
  return visible
}

fun scenicScore(row: Int, col: Int): Int {
  val tree = forest[row][col]
  var score = 1

  if (
    row == 0 ||
    row == height ||
    col == 0 ||
    col == width
  ) return 0

// up distance
  var distance = 0
  for (otherTree in forest.slice(row - 1 downTo 0)) {
    distance++
    if (otherTree[col] >= tree) break
  }
  score *= distance

// left distance
  distance = 0
  for (otherTree in forest[row].slice(col - 1 downTo 0)) {
    distance++
    if (otherTree >= tree) break
  }
  score *= distance

// right distance
  distance = 0
  for (otherTree in forest[row].slice(col + 1 until width)) {
    distance++
    if (otherTree >= tree) break
  }
  score *= distance

// down distance
  distance = 0
  for (otherTree in forest.slice(row + 1 until height)) {
    distance++
    if (otherTree[col] >= tree) break
  }
  score *= distance

  return score
}

fun a(): Int {
  var sum = 0

  for (row in 0 until height) for (col in 0 until width)
    if (visible(row, col)) sum++

  return sum
}

fun b(): Int {
  var max = 0

  for (row in 0 until height) for (col in 0 until width)
    if (scenicScore(row, col) > max)
      max = scenicScore(row, col)

  return max
}