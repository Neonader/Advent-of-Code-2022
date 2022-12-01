fun main(args: Array<String>) {
  print("Which day would you like to run? > ")

  val input = readln()
  when (input) {
    "1a" -> day01.a()
    "1b" -> day01.b()
  }
}