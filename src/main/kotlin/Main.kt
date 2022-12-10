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
    "5a" -> day05.a()
    "5b" -> day05.b()
    "6a" -> day06.a()
    "6b" -> day06.b()
    "7a" -> day07.a()
    "7b" -> day07.b()
    else -> -1
  }

  println("\nThe result is $result.")
}