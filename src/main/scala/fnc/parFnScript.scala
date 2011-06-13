package org.scratch.fnc

/**
 * Partially applied functions
 */
object parFnScript {

  def concatUpper(s1: String, s2: String): String = (s1 + ".." + s2 ).toUpperCase

  val c = concatUpper _     //partially applied - no parameters speced
  println( c("one", "two"))

  val cc = concatUpper( "one", _ :String )    // partially applied par1
  println(cc("three"))

  /**
   * p-functions will inform us when we apply parameters outside their domain
   */

  val truthier: PartialFunction[Any, String] = {
    case true => "yes"
    case "secret text" => "bingo"
  }

  val fallacy: PartialFunction[Any, String] = {
    case false => "no"
  }

  val maybe: PartialFunction[Any, String] = {
    case x => "maybe"
  }

  val tester = truthier orElse fallacy orElse maybe

  println(tester(1 == 1))
  println(tester(2 + 2 == 5))
  println(tester("circle"))
  println(tester("blue, no purple"))
  println(tester("secret text"))

}
