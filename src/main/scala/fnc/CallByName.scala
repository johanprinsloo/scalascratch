package org.scratch.fnc

/**
 * http://programming-scala.labs.oreilly.com/ch08.html#_call_by_name_call_by_value
 * http://daily-scala.blogspot.com/2009/12/by-name-parameter-to-function.html
 */
object CallByName {

  /**
   * by name
   */
  def foo(code: => Int)(conditional: => Boolean) {
    println("Not yet evaluated")
    val result = code
    println("parameter evaluated %s, is it an int ? %s " format (
      result, result.isInstanceOf[Int]))

    if (conditional) {
      //evaluated here
      println("conditional true ")
    }
  }

  foo(1)(true)


  val a = 3
  val b = 5

  foo({
    val c = a * a
    c * b
  })(a == 3)


  /**
   * by value
   */
  def foo2(code: Int) {
    println("Parameter already evaluated")
    val result = code
    println("parameter evaluated : " + result)
  }

  foo2(12)

  foo2({
    val c = a * a
    c * b
  })

  // from http://programming-scala.labs.oreilly.com/ch08.html#_call_by_name_call_by_value
  def whileAwesome(conditional: => Boolean)(f: => Unit) {
    if (conditional) {
      f
      whileAwesome(conditional)(f)
    }
  }

  var count = 0

  whileAwesome(count < 5) {
    println("still awesome")
    count += 1
  }


}