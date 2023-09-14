package day12

import java.io.File
import kotlin.math.min

val fileList = File("puzzle_input/day12.txt").readLines()

// the rows of 'S' and 'E' respectively
val startLine = fileList.indexOfFirst { line -> line.contains('S') }
val goalLine = fileList.indexOfFirst { line -> line.contains('E') }
// the coordinates of 'S' and 'E' respectively
val player = Pair(startLine, fileList[startLine].indexOf('S'))
val goal = Pair(goalLine, fileList[goalLine].indexOf('E'))
// the puzzle input with 'S' -> 'a' and 'E' -> 'z'
val heightMap = fileList.map { line -> line.replaceFirst('S', 'a').replaceFirst('E', 'z') }
// a map of the amount of steps required to reach a point
val stepMap = fileList.map { line -> line.map { 0 }.toMutableList() }
val stepBackMap = stepMap

fun step(row: Int, col: Int) {
  val height = heightMap[row][col]
  val steps = stepMap[row][col] + 1

// step up
  if (row != 0 && (stepMap[row - 1][col] > steps || stepMap[row - 1][col] == 0) && heightMap[row - 1][col] <= height + 1) {
    stepMap[row - 1][col] = steps
    step(row - 1, col)
  }
// step down
  if (row != heightMap.lastIndex && (stepMap[row + 1][col] > steps || stepMap[row + 1][col] == 0) && heightMap[row + 1][col] <= height + 1) {
    stepMap[row + 1][col] = steps
    step(row + 1, col)
  }
// step left
  if (col != 0 && (stepMap[row][col - 1] > steps || stepMap[row][col - 1] == 0) && heightMap[row][col - 1] <= height + 1) {
    stepMap[row][col - 1] = steps
    step(row, col - 1)
  }
// step right
  if (col != heightMap[0].lastIndex && (stepMap[row][col + 1] > steps || stepMap[row][col + 1] == 0) && heightMap[row][col + 1] <= height + 1) {
    stepMap[row][col + 1] = steps
    step(row, col + 1)
  }
}

fun stepBack(row: Int, col: Int) {
  val height = heightMap[row][col]
  val steps = stepMap[row][col] + 1

  // step up
  if (row != 0 && (stepBackMap[row - 1][col] > steps || stepMap[row - 1][col] == 0) && heightMap[row - 1][col] >= height - 1) {
    stepBackMap[row - 1][col] = steps
    stepBack(row - 1, col)
  }
// step down
  if (row != heightMap.lastIndex && (stepBackMap[row + 1][col] > steps || stepMap[row + 1][col] == 0) && heightMap[row + 1][col] >= height - 1) {
    stepBackMap[row + 1][col] = steps
    stepBack(row + 1, col)
  }
// step left
  if (col != 0 && (stepBackMap[row][col - 1] > steps || stepMap[row][col - 1] == 0) && heightMap[row][col - 1] >= height - 1) {
    stepBackMap[row][col - 1] = steps
    stepBack(row, col - 1)
  }
// step right
  if (col != heightMap[0].lastIndex && (stepBackMap[row][col + 1] > steps || stepMap[row][col + 1] == 0) && heightMap[row][col + 1] >= height - 1) {
    stepBackMap[row][col + 1] = steps
    stepBack(row, col + 1)
  }
}

fun a(): Int {
  step(player.first, player.second)

  return stepMap[goal.first][goal.second]
}

fun b(): Int {
  stepBack(goal.first, goal.second)

  var minSteps = Int.MAX_VALUE

  stepBackMap.forEachIndexed { row, line -> line.forEachIndexed { col, steps -> if (heightMap[row][col] == 'a' && steps != 0) minSteps = min(steps, minSteps) } }

  return minSteps
}