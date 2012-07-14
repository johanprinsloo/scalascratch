package dsl

import scala.tools.nsc._
import interpreter._
import java.io._
import scala.reflect._

object GenBin extends App {

  var i = new Interpreter(new Settings(str => println(str)))
  var res = Array[Any](null)
  i.beQuietDuring({
    i.bind("result", "Array[Any]", res)
    i.interpret("class Fred {def foo = 3}")
    i.interpret("result(0) = new Fred")
  })

  println(res(0).asInstanceOf[{def foo: Int}].foo)


}
