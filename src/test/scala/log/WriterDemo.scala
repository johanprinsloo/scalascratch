package org.scratch.log

import Writer._
import org.scalatest.FunSuite

class WriterDemo extends FunSuite {

  test("basic monadic logging") {
    val k = 41

    val r =
      for {
        a <- k withvaluelog ("starting with " + _)
        b <- (a + 7) withlog "adding 7"
        c <- (b * 3).nolog
        d <- c.toString.reverse.toInt withvaluelog ("switcheroo with " + _)
        e <- (c % 2 == 0) withlog "is even?"
      } yield e

    println("Result: " + r.a)
    println("LOG")
    println("===")
    r.log foreach println

    println("STATE")
    println("===")
    println(r)

    assert(r.a)

  }

}