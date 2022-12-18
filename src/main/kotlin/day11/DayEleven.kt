package day11

import java.io.File
import kotlin.math.roundToInt

val fileList =  File("puzzle_input/day11.txt").readLines()

class Monkey(
  val inventory: MutableList<Int>,
  val operation: (Int) -> Int,      // divides by 3 afterwards
  val test: (Int) -> Boolean,       // includes the operation
  val throwTo: Pair<Int, Int> ,     // if true -> `.first`, if false -> `.second`
  var activity: Int = 0
)

fun Monkey.inspectItems() {
  activity += inventory.size

  for (i in inventory.indices) {
    val item = inventory[i]

    if (test(item))
      monkeys[throwTo.first].inventory += operation(item)
    else
      monkeys[throwTo.second].inventory += operation(item)
  }

  inventory.clear()
}

val monkeys = mutableListOf<Monkey>()

// monkey parser
fun common() {
  for (i in fileList.indices) if (i % 7 == 0) {
    val inventory = fileList[i + 1].slice(18 until fileList[i + 1].length).split(", ").map { it.toInt() }

    val term2 = fileList[i + 2].slice(25 until fileList[i + 2].length)
    val operation =
      if (fileList[i + 2][23] == '+')
        if (term2 == "old")
        {term1: Int ->
          (term1 + term1) / 3
        }
        else
        {term1: Int ->
          (term1 + term2.toInt()) / 3
        }
      else
        if (term2 == "old")
        {term1: Int ->
          term1 * term1 / 3
        }
        else
        {term1: Int ->
          term1 * term2.toInt() / 3
        }

    val divisor = fileList[i + 3].slice(21 until fileList[i + 3].length).toInt()
    val test = { it: Int ->
      operation(it) % divisor == 0
    }

    val throwTo = Pair(fileList[i + 4][29].digitToInt(), fileList[i + 5][30].digitToInt())

    monkeys += Monkey(
      inventory as MutableList<Int>,
      operation,
      test,
      throwTo
    )
  }
}

fun a(): Int {
  common()

  var max = mutableListOf(0, 0)

  repeat(20) {
    for (monkey in monkeys) monkey.inspectItems()
  }

  for (monkey in monkeys) {
    if (monkey.activity > max[0]) {
      max[1] = max[0]
      max[0] = monkey.activity
    } else if (monkey.activity > max[1])
      max[1] = monkey.activity
  }

  return max[0] * max[1]
}

fun b(): Int {
  common()

  return -1
}