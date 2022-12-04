fun main() {
  print("Which day would you like to run? > ")

  val result = when (readln()) {
    "1a" -> day01.a()
    "1b" -> day01.b()
    "2a" -> day02.a()
    "2b" -> day02.b()
    "3a" -> day03.a()
    "3b" -> day03.b()
    "4a" -> day04.a()
    "4b" -> day04.b()
    else -> -1
  }

  println("\nThe result is $result.")
}