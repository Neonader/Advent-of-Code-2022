package day07

import java.io.File

val fileString = File("puzzle_input/day07.txt").useLines { it.toList() }

var fileSystem = HashMap<String, Int>()
var currentDirectory = "/"

fun changeDirectory(argument: String) {
  if (argument == "..") {
    var pattern = "[a-z]+/$".toRegex()
    currentDirectory = currentDirectory.replace(pattern, "")
    return
  }
  currentDirectory += "$argument/"
  fileSystem[currentDirectory] = 0
}

fun addFile(argument: MatchResult?) {
  val value = argument!!.value.toInt()
  fileSystem[currentDirectory] = fileSystem[currentDirectory]!! + value
}

fun common() {
  fileSystem[currentDirectory] = 0
  for (line in fileString.slice(1 until fileString.size)) when {
    line.startsWith("$ cd") -> {
      val argument = line.slice(5 until line.length)
      changeDirectory(argument)
    }
    line[0].isDigit()       -> {
      val argument = "[0-9]+".toRegex().find(line)
      addFile(argument)
    }
  }
}

fun a(): Int {
  common()

  var sum = 0

  for (directory in fileSystem) {
    var totalValue = directory.value
    for (child in fileSystem)
      if (child != directory && child.key.startsWith(directory.key))
        totalValue += child.value
    if (totalValue <= 100_000) sum += totalValue
  }

  return sum
}

fun b(): Int {
  common()

  var usedSpace = 0
  for (directory in fileSystem) usedSpace += directory.value
  val availableSpace = 70_000_000 - usedSpace
  val necessarySpace = 30_000_000 - availableSpace

  var optimalDirectorySize = 70_000_000

  for (directory in fileSystem) {
    var totalValue = directory.value
    for (child in fileSystem)
      if (child != directory && child.key.startsWith(directory.key))
        totalValue += child.value
    if (totalValue in necessarySpace until optimalDirectorySize)
      optimalDirectorySize = totalValue
  }

  return optimalDirectorySize
}