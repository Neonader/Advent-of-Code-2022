package day11

import java.io.File

val fileList =  File("puzzle_input/day11.txt").readLines()

class Monkey(
  val inventory: MutableList<Long>,
  val operation: (Long) -> Long,    // a: divides by 3 afterwards, b: keeps the worry level manageable
  val testA: (Long) -> Boolean,     // divides by 3
  val testB: (Long) -> Boolean,
  val throwTo: Pair<Int, Int> ,     // if true -> `.first`, if false -> `.second`
  var activity: Long = 0
)

fun Monkey.inspectItems() {
  activity += inventory.size

  for (i in inventory.indices) {
    val item = inventory[i]

    if (testA(item))
      monkeys[throwTo.first].inventory += operation(item) / 3
    else
      monkeys[throwTo.second].inventory += operation(item) / 3
  }

  inventory.clear()
}

var modulus = 1

fun Monkey.inspectItemsClumsily() {
  activity += inventory.size

  for (i in inventory.indices) {
    val item = inventory[i]

    if (testB(item))
      monkeys[throwTo.first].inventory += operation(item) % modulus
    else
      monkeys[throwTo.second].inventory += operation(item) % modulus
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
        { term1: Long ->
          (term1 + term1)
        }
        else
        { term1: Long ->
          (term1 + term2.toLong())
        }
      else
        if (term2 == "old")
        { term1: Long ->
          term1 * term1
        }
        else
        { term1: Long ->
          term1 * term2.toLong()
        }

    val divisor = fileList[i + 3].slice(21 until fileList[i + 3].length).toInt()
    val testA = { it: Long ->
      operation(it) / 3 % divisor == 0L
    }
    val testB = { it: Long ->
      operation(it) % divisor == 0L
    }

    val throwTo = Pair(fileList[i + 4][29].digitToInt(), fileList[i + 5][30].digitToInt())

    modulus *= divisor

    monkeys += Monkey(
      inventory as MutableList<Long>,
      operation,
      testA,
      testB,
      throwTo
    )
  }
}

fun a(): Long {
  common()

  val max = mutableListOf(0L, 0L)

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

fun b(): Long {
  common()

  val max = mutableListOf(0L, 0L)

  repeat(10_000) {
    for (monkey in monkeys) monkey.inspectItemsClumsily()
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