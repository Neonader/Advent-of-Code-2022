fun main(args: Array<String>) {
  print("Which day would you like to run? > ")

  val result = when (readln()) {
    "1a" -> day01.a()
    "1b" -> day01.b()
    else -> -1
  }

  println("The result is $result.")
}